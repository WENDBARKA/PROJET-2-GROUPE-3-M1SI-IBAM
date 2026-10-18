
# Projet SOAP


## 🎯 Objectif

Développer une application SOAP en Java avec **Apache CXF**, intégrant **Apache NiFi** pour l’ETL, **PostgreSQL** pour la base de données, et un monitoring complet avec **Prometheus** et **Grafana**. Les tests sont réalisés avec **SoapUI**.

## 🛠️ Prérequis

Avant de lancer le projet, assurez-vous d’avoir installé :
- Java 11+  
- Maven  
- Apache NiFi  
- PostgreSQL  
- Prometheus  
- Grafana  
- SoapUI  

## 📂 Structure du projet

PROJET/ │ ├── src/ # Code Java SOAP (Apache CXF) ├── configs/ # Configurations │ ├── nifi-template.xml │ ├── prometheus.yml │ ├── grafana.ini │ └── SOAP_monitoring_dashbord_grafana.json ├── tests/ # Tests SoapUI │ └── TestSOAPClient-soapui-project.xml ├── docs/ # Documentation │ ├── rapport.pdf │ └── procedure.docx ├── clientdb.sql # Base PostgreSQL ├── pom.xml # Projet Maven └── README.md # Ce fichier
je conseille de prendre connaissance du rapport fournis dans le github il y a des captures d'ecran qui pourront vous aider a bien comprendre 

## ⚙️ Installation et configuration

### 1. Base de données PostgreSQL

- Créez une base `clientdb`.  
- Importez le fichier `clientdb.sql`.  
- Identifiants par défaut :  
  - **Utilisateur** : postgres  
  - **Mot de passe** : Boukys54@ (modifiable selon votre installation).

### 2. Apache NiFi

- Lancez NiFi :  
  -run-nifi.bat
  
-Accédez à https://localhost:8443/nifi.

-Importez le template nifi-template.xml.

-Placez le flux sur le canvas et démarrez les processeurs.

### 3. Service SOAP (Java + CXF)

-Allez dans le dossier SOAP_SERVICE/soap-cxf-service du github télécharger.

-Compilez et lancez :
  -mvn clean install
  -mvn exec:java -Dexec.mainClass="org.example.Server"
  
-Le service SOAP démarre et expose son WSDL.

-Les métriques sont automatiquement envoyées vers Prometheus.

### 4. Prometheus

-Seuls les fichiers de configuration sont inclus dans ce dépôt (prometheus.yml). Les exécutables (prometheus.exe, promtool.exe) et le dossier data/ sont exclus car ils sont lourds et recréés automatiquement.

-Téléchargez Prometheus depuis prometheus.io/download.

-Placez prometheus.yml  qui est dans le github dans le dossier d’installation.

-Lancez Prometheus :
  -prometheus --config.file=prometheus.yml --web.listen-address=:9091
  
-Interface : http://localhost:9091.

### 5. Grafana

-Seuls les fichiers de configuration et dashboards JSON sont inclus. Les exécutables et dossiers runtime sont exclus.

-Téléchargez Grafana depuis grafana.com/download.

-Lancez Grafana : http://localhost:3000.

-Importez le dashboard SOAP_monitoring_dashbord_grafana.json.

### 6. SoapUI

-Ouvrez SoapUI.

-Importez le projet TestSOAPClient-soapui-project.xml.

-Envoyez une requête SOAP (exemple : saveClient).

-Les métriques sont automatiquement enregistrées par Prometheus et visualisées dans Grafana.

### 7. Métriques Prometheus utilisées

-soap_requests_duration_seconds_count → nombre total de requêtes SOAP.

-soap_requests_duration_seconds_sum → somme des durées des requêtes.

-rate(soap_requests_duration_seconds_sum[1m]) / rate(soap_requests_duration_seconds_count[1m]) → latence moyenne sur 1 minute.

-soap_requests_duration_seconds_max → durée maximale observée.

-rate(soap_requests_duration_seconds_count[1m]) → débit du service (requêtes par seconde).

### 8. Architecture du projet
-Apache NiFi : ETL, extraction et transformation des données depuis PostgreSQL.

-Base PostgreSQL : stockage des clients.

-SOAP Service (Java + CXF) : expose les données via une API SOAP.

-SoapUI : tests fonctionnels du service SOAP.

-Prometheus : collecte des métriques (latence, nombre de requêtes, débit).

-Grafana : visualisation en temps réel des métriques.

### 9. Conclusion
-Ce projet illustre une chaîne complète d’intégration et de monitoring :

-Extraction et transformation des données avec NiFi.

-Exposition via un service SOAP en Java.

-Collecte des métriques avec Prometheus.

-Visualisation en temps réel avec Grafana.

-Tests fonctionnels avec SoapUI.

NB: je vous conseille de vraiment regarder le rapport pour verification des tests effectuées, Merci!!!!!!!!
