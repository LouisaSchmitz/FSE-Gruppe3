# Fortgeschrittene Softwareentwicklung
![project](https://img.shields.io/badge/Modul-FSE-red)
![size](https://img.shields.io/github/repo-size/LouisaSchmitz/FSE-Gruppe3?logo=github)
![commit](https://img.shields.io/github/last-commit/LouisaSchmitz/FSE-Gruppe3?logo=git)  
In dieser Hausarbeit geht es darum, eine Anwendung in einer **Microservice-Architektur** zu implementieren. Jede Gruppe bearbeitet dazu eine eigene Fallstudie.

## Fallbeispiel - Gruppe 3 (Test Management)
- As a test manager I want to crud user stories for testing.
- As a test manager I want to crud test cases for user stories.
- As a test manager I want to set user stories to ‘ready for test’.
    - Given a user story with at least one test case. When the test manager sets a user story as ‘ready for test’, all test cases attached to user story will be set to ‘ready for test’.
- As a tester I want to see test cases in state ‘ready for test’.
- As a tester I want to log test executions.

## Aufgabenstellung

### 1. Identifizierung von Bounded Contexts
Identifizieren Sie mindestens zwei sinnvolle Bounded Contexts (Microservices) für die Fallstudie. Modellieren Sie die Bounded Contexts mit den Mitteln der DDD als UML-Klassendiagramm, d.h. identifizieren Sie Aggregates, Entities, Value Objects, Domain Services, Repositories und Application Services. Die Angabe von Attributen ist ausreichend, die Angabe von Methoden ist nicht erforderlich.  

**[📝 PDF-Dokument](/pdf/Tactical-Design.pdf)**  

![Bounded Context](/pdf/Tactical-Design.jpg)  

### 2. Implementierung des Fallbeispiels
Implementieren Sie einen Ausschnitt der Fallstudie mit Spring Boot. Wählen Sie den Ausschnitt so, dass es sinnvoll ist, zwei Microservices zu entwickeln, die asynchron miteinander kommunizieren. Die Implementierung muss keine Benutzerschnittstelle enthalten, es genügt, wenn die Funktionalität über REST-Endpoints verfügbar ist.  

**[📝 PDF-Dokument](/pdf/Package-Struktur.pdf)**  
**[⚙️ Source Code](https://github.com/LouisaSchmitz/FSE-Gruppe3/releases/tag/aufgabe-2)**

### 3. Implementierung des Publish/Subscribe-Prinzips
Das Veranstaltungsbeispiel zeigt eine asynchrone Kommunikation per Broadcast. Implementieren Sie für Ihre Anwendung eine asynchrone Kommunikation nach dem Publish/Subscribe-Prinzip. Nutzen Sie die Konzepte "Topic Exchange" und "Routing Key" von RabbitMQ.  

**[⚙️ Source Code](https://github.com/LouisaSchmitz/FSE-Gruppe3/releases/tag/aufgabe-3)**

### 4. Implementierung der Kommunikation mit Kafka
Spring Boot unterstützt neben RabbitMQ auch Kafka für die asynchrone Kommunikation. Implementieren Sie für Ihre Anwendung die asynchrone Kommunikation mit Kafka.  

**[📝 PDF-Dokument](/pdf/LiteraturanalyseKafkaRabbitMQ.pdf)**  
**[⚙️ Source Code](https://github.com/LouisaSchmitz/FSE-Gruppe3/releases/tag/aufgabe-4)**

### 5. Implementierung von Tests
Ein Vorteil einer DDD-basierten Architektur ist, dass die Fachdomäne (Aggregates + Entities + Value Object + Domain Services) separat getestet werden kann. Implementieren Sie solche Tests als automatisierte Tests für die Fachdomäne.  

**[📝 PDF-Dokument](/pdf/Automatische_Tests.pdf)**  
**[⚙️ Source Code](https://github.com/LouisaSchmitz/FSE-Gruppe3/releases/tag/aufgabe-5)**

## Projekt-Setup
Um die Microservices benutzen und testen zu können, müssen sowohl eine H2-Datenbank als auch zwei Docker-COntainer betrieben werden.

### SQL-Skript
Für die korrekte Ausführung der Microservices wird eine lokale H2-Datenbank benötigt. Das SQL-Skript zur Erstellung der Datenbank und zum Befüllen mit Testdaten finden Sie **[hier](/setup/sql.txt)**. In der Datei **application.properties** der Microservices muss der Dateipfad zur lokalen Datenbank geändert werden.  

**```spring.datasource.url=jdbc:h2:Link-To-Folder/testManager```**  
**```spring.datasource.url=jdbc:h2:Link-To-Folder/testing```**

### Docker-Container
Die Messaging-Dienste können als lokale Docker-Container installiert werden, um die Funktionalität der Microservices zu gewährleisten. Mithilfe der Datei **[docker-compose.yaml](/setup/docker-compose.yml)** können die Docker-Container lokal installiert werden.  

**```docker compose up -d```**  

## Projekt-Mitglieder
- [Tobias Piepers](https://github.com/TP1901)
- [Louisa Schmitz](https://github.com/LouisaSchmitz)
- [Bjarne Christel](https://github.com/B4ZZ3)
