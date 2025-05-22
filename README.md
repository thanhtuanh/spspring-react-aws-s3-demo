# 📁 Spring React AWS S3 Demo

Ein modernes Fullstack-Projekt mit **Spring Boot**, **React**, **AWS S3** und **Docker**, ideal als Demonstration für Cloud-Kompetenz in Bewerbungssituationen.

---

## 🚀 Demo

* 🌐 Frontend: [http://localhost:3000](http://localhost:3000)
* 🛠️ API Backend: [http://localhost:8080/api/products](http://localhost:8080/api/products)
* ☁️ Beispielbild in S3: [https://dnguyenawsbucket.s3.eu-west-1.amazonaws.com/beispiel.jpg](https://dnguyenawsbucket.s3.eu-west-1.amazonaws.com/beispiel.jpg)

---

## 💼 Bewerbungs-Kontext

Ziel dieses Projekts ist die praxisnahe Demonstration moderner Technologien und Cloud-Erfahrung:

* **Spring Boot (Java 17)** – REST-API mit Cloud-Anbindung
* **React** – modernes UI mit Dateiupload, Metadatenanzeige & Vorschau
* **AWS S3** – Presigned URL Upload mit sicherem Zugriff
* **CI/CD** – über GitHub Actions automatisiert
* **Dockerisierung** – vollständiger lokaler Aufbau via `docker-compose`
* **Dokumentation** – klar strukturiertes README

---

## 🔐 Sicherheit & Best Practices

* **Presigned URLs** schützen AWS-Zugangsdaten: Upload erfolgt direkt vom Browser.
* `.env` wird **nicht** ins Repository eingecheckt (siehe `.gitignore`).
* AWS-Zugangsdaten nur lokal/in Render UI setzen, niemals hardcoded.
* S3 Bucket mit **CORS-Konfiguration**:

```json
[
  {
    "AllowedOrigins": ["*"],
    "AllowedMethods": ["GET", "PUT"],
    "AllowedHeaders": ["*"]
  }
]
```

---

## 📦 Features

| Bereich    | Funktion                                                        |
| ---------- | --------------------------------------------------------------- |
| Upload     | Direkt-Upload nach AWS S3 mit Vorschau                          |
| Metadaten  | Bildgröße, Typ, Name, Abmessungen, Größe                        |
| API        | RESTful via Spring Boot                                         |
| Speicher   | PostgreSQL für Metadaten                                        |
| Sicherheit | Keine Speicherung von Dateien im Backend                        |
| CI/CD      | Automatisierter Build via GitHub Actions für Frontend + Backend |
| Container  | Komplette Umgebung per `docker-compose`                         |

---

## 🔧 Lokales Setup

### Voraussetzungen

* Docker + Docker Compose
* `.env` Datei mit AWS-Zugangsdaten:

```env
AWS_REGION=eu-west-1
AWS_S3_BUCKET=dnguyenawsbucket
AWS_ACCESS_KEY=dein_key
AWS_SECRET_KEY=dein_secret
```

### Starten

```bash
docker-compose --env-file .env up --build
```

Zugänglich unter:

* Frontend: [http://localhost:3000](http://localhost:3000)
* Backend: [http://localhost:8080](http://localhost:8080)

---

## 🧪 Beispiel: curl Upload

```bash
curl -X GET http://localhost:8080/api/products/presigned-upload-url/test.jpg
# ➜ gibt URL zurück
curl -X PUT "<URL>" -H "Content-Type: image/jpeg" --upload-file ./test.jpg
```

---

## 🏁 Deployment

### 🌍 Render.com

1. Repository bei GitHub verbinden

2. Zwei Webservices erstellen:

   * **Backend:** Spring Boot App (Docker oder Java Webservice)
   * **Frontend:** React (Static Site)

3. **Umgebungsvariablen setzen**

```env
SPRING_DATASOURCE_URL=jdbc:postgresql://...:5432/mycloud
SPRING_DATASOURCE_USERNAME=postgres
SPRING_DATASOURCE_PASSWORD=postgres
AWS_REGION=eu-west-1
AWS_S3_BUCKET=dnguyenawsbucket
AWS_ACCESS_KEY=...
AWS_SECRET_KEY=...
```

4. CI/CD mit GitHub Actions oder Render Auto-Deploy nutzen

### 🏛 Railway.app (für PostgreSQL)

1. Neues PostgreSQL-Projekt erstellen
2. Connection String kopieren
3. In `SPRING_DATASOURCE_URL` einsetzen

**Beispiel:**

```
jdbc:postgresql://pg-12345678.rw.rds.com:5432/mycloud
```

---

## 📄 Lizenz

MIT License © 2025 Duc Thanh Nguyen
