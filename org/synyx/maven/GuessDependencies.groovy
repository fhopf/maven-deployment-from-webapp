package org.synyx.maven

if (args.length == 0) {
    println "Missing argument for directory to process";
    return;
}

def dir = args[0];

assert dir.endsWith("/");

def dependencies = new ArrayList();

// guess all dependencies from filenames
new File(dir).listFiles().each() {
    dependencies.add(new Dependency(it.name));
}

// write the config file with all dependency information
new File(dir + "dependencies.properties").withWriter() { out ->
    dependencies.each() { dep ->
        println "processing ${dep.filename}";
        out.writeLine("${dep.filename}=${dep.groupId}:${dep.artifactId}:${dep.version}");
    }
}

// write an empty properties file for project information
new File(dir + "project.properties").withWriter() { out ->
    out.println(new ProjectConfig());
}

