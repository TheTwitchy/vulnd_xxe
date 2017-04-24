# vulnd_xxe
A server vulnerable to XXE that can be used to test payloads using the [xxer tool](https://github.com/TheTwitchy/xxer), or any other tool for that matter. It's written in Java because all Java servers are vulnerable to XXE and I love pain (possibly a slight exaggeration on both points). In reality, I needed something on which to test [xxer](https://github.com/TheTwitchy/xxer) while I was writing it and figured something like this could be useful, both for me and others.

## Target Audience
Penetration testers and developers who want to learn what XXE injection is, and the impact it can have. Also people who want to browse the Internet and thier own filesystem in the most convuluted and painful manner imaginable.

## Examples
Coming soon...

## Running
### Requirements
  * JDK 1.8+
  * Maven
### Building
  * ``mvn compile``
#### Building a WAR
  * ``mvn -Pwar package``
### Running
  * ``mvn exec:java``
  * Point your browser at (http://localhost:8081).