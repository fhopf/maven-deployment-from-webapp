package org.synyx.maven

class Dependency {

    static regex = /(.*?)-([0-9]+.*).jar/;

    String groupId;
    String artifactId;
    String version;

    String filename;

    Dependency(String filename) {
        def matcher = ( filename =~ regex );
        if (matcher.matches()) {
            this.groupId = matcher[0][1];
            this.artifactId = matcher[0][1];
            this.version = matcher[0][2];
        } else {
            if (filename != null) {
                this.groupId = (filename - '.jar');
                this.artifactId = (filename - '.jar');
            } else {
                this.groupId = filename;
                this.artifactId = filename;
            }
            this.version = "";
        }
        this.filename = filename;
    }

    String toString() {
        return "${groupId}.${artifactId}-${version}";
    }
}

