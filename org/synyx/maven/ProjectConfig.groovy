package org.synyx.maven

class ProjectConfig {

    String groupId = "";
    String artifactId = "";
    String packaging = "";
    String repositoryId = "";
    String url = "";

    ProjectConfig() {
        
    }

    ProjectConfig(Properties props) {
        groupId = props.groupId;
        artifactId = props.artifactId;
        packaging = props.packaging;
        repositoryId = props.repositoryId;
        url = props.url;
    }

    String toString() {
        def writer = new StringWriter();
        writer.println("groupId=${groupId}");
        writer.println("artifactId=${artifactId}");
        writer.println("packaging=${packaging}");
        writer.println("repositoryId=${repositoryId}");
        writer.println("url=${url}");
        return writer.toString();
    }


}

