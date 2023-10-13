FROM payara/micro:6.2023.9-jdk17
COPY target/jakartaee-hello-world.war $DEPLOY_DIR/ROOT.war
