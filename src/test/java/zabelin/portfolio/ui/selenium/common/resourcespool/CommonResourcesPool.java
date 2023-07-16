package zabelin.portfolio.ui.selenium.common.resourcespool;

import zabelin.portfolio.core.common.Log;

import java.util.*;

/**
 * Created by
 *
 * @author Aleksei Aksenov
 * on 10/15/2019.
 */
public abstract class CommonResourcesPool {

    protected static final int userFindTimeout = 300; // in seconds

    protected static volatile Map<String, Map<Blockable, String>> resourcesCollection; // type : <Resource : test>

    protected static volatile Set<String> testsHoldingResources;

    public CommonResourcesPool() {
        if (testsHoldingResources == null)
            testsHoldingResources = new HashSet<>();
        if (resourcesCollection == null)
            resourcesCollection = new HashMap<>();
    }

    public int getBlockableObjectsCount(String userKind) {
        if (userKind == null || userKind.trim().equals("") || !resourcesCollection.containsKey(userKind)) {
            Log.debug(String.format("No such Blockable object type in the collection: \"%s\"", userKind));
            return 0;
        }

        return resourcesCollection.get(userKind).entrySet().size();
    }

    public Blockable getObject(String resourceType, String testIdentity) throws InterruptedException {
        if (!resourcesCollection.containsKey(resourceType))
            throw new IllegalArgumentException(String.format("No such Blockable object type in the collection: \"%s\"", resourceType));

        Blockable obj;
        // needed in case when threads count is more than users count
        long endTime = System.currentTimeMillis() + userFindTimeout * 1000;
        while (true) {
            obj = findFreeObject(resourceType, testIdentity);
            if (obj != null) return obj;

            Log.debug(String.format("No \"%s\" Blockable object found for test \"%s\" yet", resourceType, testIdentity));
            if (System.currentTimeMillis() > endTime)
                throw new InterruptedException("Cannot find free Blockable object in " + userFindTimeout + " seconds.");
            Thread.sleep(500);
        }
    }

    private Blockable findFreeObject(String userKind, String testIdentity) {
        try {
            if (!resourcesCollection.containsKey(userKind))
                throw new IllegalArgumentException(String.format("No such Blockable object type in the collection: \"%s\"", userKind));

            synchronized (resourcesCollection) {
                Map<Blockable, String> users = resourcesCollection.get(userKind);
                Blockable obj = users.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue() == null)
                        .findFirst().get().getKey();


                resourcesCollection.get(userKind).replace(obj, testIdentity);
                testsHoldingResources.add(testIdentity);

                return obj;
            }
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean release(String testIdentity) {
        if (!testsHoldingResources.contains(testIdentity))
            return true;
        boolean[] removed = new boolean[1];
        synchronized (resourcesCollection) {
            Log.action("After-jobs started in " + testIdentity);
            for (Map<Blockable, String> collection : resourcesCollection.values()) {
                collection.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue() != null && entry.getValue().equals(testIdentity))
                        .forEach(entry -> {
                            removed[0] = true;
                            entry.setValue(null);
                            Log.debug(String.format("Object %s has been released by test \"%s\"",
                                    entry.getKey().getIdentity(), testIdentity));
                        });
            }
            Log.action("After-jobs finished in " + testIdentity);
        }

        testsHoldingResources.remove(testIdentity);
        return removed[0];
    }

}
