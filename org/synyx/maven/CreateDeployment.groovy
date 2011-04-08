package org.synyx.maven

if (args.length == 0) {
    println "Missing argument for directory to process";
    return;
}

def dir = args[0];

assert dir.endsWith("/")

def pomLocation = dir + "pom.xml";

def dependencies = [];

new File(dir + "dependencies.properties").splitEachLine("[=|:]") { data ->
    println "Reading dependency" + data;
    dependencies.add(new Dependency(filename : data[0], groupId : data[1], artifactId : data[2], version : data[3]));
}

def props = new Properties();
props.load(new FileReader(dir + "project.properties"));
def config = new ProjectConfig(props);

def projectDependency;
def allWithoutProject = [];

new File(dir + "deploy.sh").withWriter() { out ->
    dependencies.each() { dep ->
        println "Processing " + dep;
        def deployment = "mvn deploy:deploy-file -Durl=${config.url} -DrepositoryId=${config.repositoryId} -Dfile=${dep.filename} -DgroupId=${dep.groupId} -DartifactId=${dep.artifactId} -Dversion=${dep.version} -Dpackaging=jar";
        if (config.groupId.equals(dep.groupId) && config.artifactId.equals(dep.artifactId)) {
            projectDependency = dep;
            deployment = deployment + " -DpomFile=pom.xml"
        } else {
            allWithoutProject.add(dep);
        }
        out.writeLine(deployment);
    }
}

def projectPom = new Pom(project : projectDependency, dependencies : allWithoutProject);
new File(dir + "pom.xml").withWriter() { out ->
    out.println(projectPom.toString());
}
