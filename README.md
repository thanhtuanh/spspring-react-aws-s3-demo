# 🌩️ MyCloud – Fullstack Produkt-Manager (Spring Boot, React, AWS S3, Docker)

**Ein modernes Fullstack-Projekt zur Demonstration meiner Kompetenzen in Cloud-Entwicklung, moderner Webarchitektur und CI/CD.**  
Ideal als Showcase als Fullstack-Entwickler (Java/React/Cloud).

---

## 🚀 Projektüberblick

Dieses Projekt vereint alle Fähigkeiten, die heute von einem erfahrenen Fullstack-Developer erwartet werden:

- **Backend:** Spring Boot (Java 17), REST API, Security (JWT), AWS S3 Integration, PostgreSQL, Dockerisierung, CI/CD
- **Frontend:** React, modernes UI/UX, File-Upload mit Vorschau und Metadaten, Authentifizierung, Bootstrap
- **Cloud/DevOps:** AWS S3 (Presigned URLs), Docker Compose für lokale Entwicklung, GitHub Actions für automatisierte Builds, Monitoring mit Spring Actuator

---

## 💡 Warum dieses Projekt für Ihre Auswahl relevant ist

- **End-to-End:** Zeigt Kompetenz in allen Schichten – vom Datenbankmodell bis zum modernen Frontend.
- **Security-Bewusstsein:** JWT-Authentifizierung, keine Speicherung sensibler Daten im Frontend.
- **Cloud Readiness:** Direkte Integration von AWS S3, konfigurierbar für echtes Cloud-Deployment.
- **DevOps Mindset:** Komplett automatisierter Build & Test via GitHub Actions, Infrastructure-as-Code (Docker Compose).
- **Clean Code & Testing:** Umfangreiche Unit- und Integrationstests (Spring, JUnit, React Testing Library).
- **Sehr gute Dokumentation:** Verständlich für Entwickler UND Entscheider.

---

## 🧑‍💻 Tech Stack

| Bereich    | Technologie                         |
| ---------- | ----------------------------------- |
| Backend    | Spring Boot, Spring Security, JPA   |
| Auth       | JWT, Custom UserDetailsService      |
| Cloud      | AWS S3, Presigned URLs, Actuator    |
| DB         | PostgreSQL (Docker), Flyway         |
| Frontend   | React, Bootstrap, Axios             |
| DevOps     | Docker, Docker Compose, GitHub Actions |
| Tests      | JUnit, Mockito, React Testing Library |

---

## 🔒 Security & Best Practices

- **JWT Authentication:** Sicheres Login, Backend prüft Token pro Request.
- **Presigned URLs:** Uploads direkt zu S3, keine AWS-Keys im Frontend.
- **Keine Secrets im Code:** Sensitive Daten nur via .env / Umgebungsvariablen.
- **CORS-Konfiguration:** S3 und Backend erlauben nur nötige Requests.

---

## 🗂️ Architektur & Features

### Backend (Spring Boot)

- **RESTful API:** `/api/products`, `/api/auth/login`, Presigned URL Endpoints
- **Image-Upload:** Files landen direkt in AWS S3, Metadaten (Größe, Dimensionen, Typ) werden persistiert.
- **Monitoring:** Health, Metrics, Info via Actuator
- **Testing:** Unit/Integration mit MockMvc, JPA, Auth

### Frontend (React)

- **Login-Formular:** Authentifizierung gegen Spring Boot API
- **Produktverwaltung:** Upload mit Vorschau, Metadatenanzeige, Delete-Funktion
- **Responsives UI:** Bootstrap-basiert
- **State Management:** React Hooks

### DevOps / CI/CD

- **Builds:** Separate Pipelines (Backend + Frontend) mit GitHub Actions (Build, Test, optional Docker Build)
- **Docker Compose:** Lokale Entwicklung mit einem Befehl (`docker-compose up`)
- **Einfache Cloud-Migration:** Deploybar auf Render, AWS, Railway o.ä.

---
## 📝 Demo-Screenshot – Projektüberblick

[Screenshot](./access/scrennshot/demo-screenshot.pdf)

Der beigefügte Screenshot vermittelt einen umfassenden Überblick über die wichtigsten Funktionen, Architekturkomponenten und DevOps-Prozesse des Projekts. Die Präsentation ist so gestaltet, dass sie sowohl technische Tiefe als auch Nutzerfreundlichkeit und eine professionelle Umsetzung zeigt.

**Frontend UI**
- Login-Formular: Erfolgreiche und fehlerhafte Logins
- Produktübersicht: Mit Bildern, Metadaten, Delete-Button
- Upload-Formular: Bildvorschau, Fortschrittsbalken

**Backend & Cloud**
- AWS S3 Bucket mit hochgeladenen Bildern (AWS Console)
- Monitoring: Spring Boot Actuator Health- und Info-Endpunkte
- CI/CD: GitHub Actions mit grünen Build-Badges, Build-Logs

**Code & Architektur**
- API-Design: Auszug aus `ProductController.java`
- Upload-Logik: Ausschnitt aus `ProductForm.js`
- Deployment: YAML-Konfiguration des GitHub Actions Workflows

**DevOps & Betrieb**
- Docker Compose Übersicht (`docker ps`)
- Terminal-Logs von Build und Test
- Beispiel einer `.env`-Datei (ohne Secrets)

*Der Screenshot deckt alle relevanten Aspekte von Frontend, Backend, Cloud, Architektur und DevOps ab und unterstreicht meine umfassenden Fullstack-Kompetenzen.*
---

## 🏁 So starten Sie das Projekt

### 1. Klonen & vorbereiten

```bash
git clone <REPO-URL>
cd mycloud
cp .env.example .env  # Füllen Sie Ihre AWS/Datenbankdaten ein
```

### 2. Starten (lokal, mit Docker)

```bash
docker-compose --env-file .env up --build
```
- Frontend: http://localhost:3000
- Backend: http://localhost:8080

### 3. Deploy in Cloud (z.B. Render.com, Railway)

- Projekt als Webservice anlegen
- Umgebungsvariablen setzen (siehe `.env`)
- Auto-Deploy per GitHub Actions

---

## 📄 Weiterführende Links

- [Spring Boot](https://spring.io/projects/spring-boot)
- [React](https://react.dev/)
- [AWS S3](https://aws.amazon.com/s3/)
- [Docker](https://www.docker.com/)
- [GitHub Actions](https://github.com/features/actions)

---

## 🌍 Geplantes Auto-Deployment (Render.com/Fly.io)

**Auto-Deployment in die Cloud ist als nächster Schritt geplant:**  
Bei jedem Commit auf den Haupt-Branch wird das Projekt zukünftig automatisch auf Plattformen wie Render.com oder Fly.io gebaut und online bereitgestellt.

**Vorteile:**  
- Stets aktuelle Live-Demo für Recruiter und Fachteams
- Zeigt DevOps-Kenntnisse (CI/CD, Cloud Native)
- Einfach erweiterbar für weitere Cloud-Anbieter

*Sobald aktiviert, werden Live-URL und Screenshots ergänzt.*

**MIT License © 2025 Duc Thanh Nguyen**
