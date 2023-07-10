package zabelin.portfolio.core.common;

import java.io.File;

class DirectoryFactory {
    DirectoryFactory() {
    }

    public static void createDirectory(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            System.out.println("creating directory: " + dir);
            boolean result = false;

            try {
                dir.mkdirs();
                result = true;
            } catch (SecurityException var4) {
                var4.printStackTrace();
            }

            if (result) {
                System.out.println(path + " created!");
            }
        } else {
            System.out.println(path + " already exists. Will not Create!");
        }

    }
}
