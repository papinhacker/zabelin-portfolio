package zabelin.portfolio.ui.selenium.common.resourcespool;

import zabelin.portfolio.core.common.Log;

import java.lang.reflect.InvocationTargetException;
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

    protected static volatile Map<String, List<Operations>> doBeforeCollection;
    protected static volatile Map<String, List<Operations>> doAfterCollection;

    protected static volatile Set<String> testsHoldingResources;

    public CommonResourcesPool() {
        if (testsHoldingResources == null)
            testsHoldingResources = new HashSet<>();
        if (doAfterCollection == null)
            doAfterCollection = new HashMap<>();
        if (doBeforeCollection == null)
            doBeforeCollection = new HashMap<>();
        if (resourcesCollection == null)
            resourcesCollection = new HashMap<>();
    }

    public void pushBeforeResources(String testIdentity, Operations... resources) {
        synchronized (doBeforeCollection) {
            if (resources != null && resources.length > 0) {
                List<Operations> operationsList = Arrays.asList(resources);
                doBeforeCollection.put(testIdentity, operationsList);
            }
        }
    }

    public void pushAfterResources(String testIdentity, Operations... resources) {
        synchronized (doAfterCollection) {
            if (resources != null && resources.length > 0) {
                List<Operations> operationsList = Arrays.asList(resources);
                doAfterCollection.put(testIdentity, operationsList);
            }
        }
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

                Log.info("Before-jobs started in " + testIdentity);
                if (doBeforeCollection.containsKey(testIdentity)) {
                    List<Operations> operations = doBeforeCollection.get(testIdentity);
                    if (operations != null)
                        operations.stream().forEach(res -> {
                            try {
                                Operations[] resArr = new Operations[1];
                                resArr[0] = res;
                                res.getProcessor().getMethod("process", User.class, Operations[].class)
                                        .invoke(null, obj, resArr);
                            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                                Log.error("Unable to process before tasks for blocked object " + obj.getIdentity() + " in the test " + testIdentity);
                                e.printStackTrace();
                            }
                        });
                }
                Log.info("Before-jobs finished in " + testIdentity);

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
                            if (doAfterCollection.containsKey(testIdentity)) {
                                List<Operations> operations = doAfterCollection.get(testIdentity);
                                operations.stream().forEach(res -> {
                                    try {
                                        Operations[] resArr = new Operations[1];
                                        resArr[0] = res;
                                        res.getProcessor().getMethod("process", User.class, Operations[].class)
                                                .invoke(null, entry.getKey(), resArr);
                                    } catch (NoSuchMethodException | IllegalAccessException |
                                             InvocationTargetException e) {
                                        Log.error("Unable to process object releasing: " + entry.getKey().getIdentity());
                                        e.printStackTrace();
                                    }
                                });
                            }

                            removed[0] = true;
                            entry.setValue(null);
                            Log.debug(String.format("Object %s has been released by test \"%s\"",
                                    entry.getKey().getIdentity(), testIdentity));
                        });
            }
            Log.action("After-jobs finished in " + testIdentity);
        }

        doBeforeCollection.remove(testIdentity);
        doAfterCollection.remove(testIdentity);
        testsHoldingResources.remove(testIdentity);
        return removed[0];
    }

}
