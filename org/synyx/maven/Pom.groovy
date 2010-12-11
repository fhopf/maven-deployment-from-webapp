package org.synyx.maven

import groovy.xml.MarkupBuilder

class Pom {

    List<Dependency> dependencies;
    Dependency project;

    String toString() {
        def writer = new StringWriter();
        def xmlBuilder = new MarkupBuilder(writer);

        xmlBuilder.project('xmlns' : 'http://maven.apache.org/POM/4.0.0',
            'xmlns:xsi' : 'http://www.w3.org/2001/XMLSchema-instance',
            'xsi:schemaLocation' : 'http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd') {
            modelVersion('4.0.0')
            groupId(project.groupId)
            artifactId(project.artifactId)
            packaging('jar')
            version(project.version)
            name(project.artifactId)
            dependencies() {
                dependencies.each() { dep ->
                    dependency {
                        groupId(dep.groupId)
                        artifactId(dep.artifactId)
                        version(dep.version)
                    }
                }
            }
        }
        return writer.toString();
    }

}

