**# 🧠 Customer Intelligence & Segmentation**



\> An end-to-end unsupervised machine learning application that analyzes customer behavior using RFM analysis and K-Means clustering, then serves customer segments through FastAPI, Streamlit, and a native Android application.



![Python]\(https\://img.shields.io/badge/Python-3.x-blue?logo=python)

![FastAPI]\(https\://img.shields.io/badge/FastAPI-REST%20API-009688?logo=fastapi)

![Streamlit]\(https\://img.shields.io/badge/Streamlit-Web%20App-FF4B4B?logo=streamlit)

![Kotlin]\(https\://img.shields.io/badge/Kotlin-Android-7F52FF?logo=kotlin)

![Jetpack Compose]\(https\://img.shields.io/badge/Jetpack%20Compose-UI-4285F4)

![scikit-learn]\(https\://img.shields.io/badge/scikit--learn-ML-F7931E?logo=scikitlearn)

![AWS EC2]\(https\://img.shields.io/badge/AWS-EC2-FF9900?logo=amazonaws)

![GitHub]\(https\://img.shields.io/badge/GitHub-Repository-181717?logo=github)



**---**



**## 📌 Overview**



**\*\*Customer Intelligence & Segmentation\*\*** is an end-to-end machine learning project built to understand the complete journey from **\*\*transaction data and exploratory analysis to customer segmentation, API serving, cloud deployment, web integration, and Android integration\*\***.



The project includes:



\- 🧠 RFM feature engineering

\- 🎯 K-Means clustering as the main production model

\- 🌳 Hierarchical Clustering comparison with dendrogram analysis

\- 🔵 DBSCAN analysis for density-based clustering and noise detection

\- 📉 PCA for dimensionality reduction and visualization

\- 📊 Silhouette Score for cluster evaluation

\- ⚡ FastAPI REST API

\- 🌐 Streamlit Web Application

\- 📱 Native Android Application using Kotlin and Jetpack Compose

\- ☁️ AWS EC2 deployment

\- 🔄 systemd service for persistent backend execution



Both Streamlit and Android communicate with the same FastAPI prediction backend.



**---**



**# 🚀 Live Applications & Resources**



\| Resource | Link |

\|---|---|

\| 🌐 Streamlit Web Application | https\://customer-intelligence-segmentation.streamlit.app/ |

\| 💻 Source Code | https\://github.com/TheSangamX/customer-intelligence-segmentation |

\| 🧪 FastAPI Swagger API | http\://3.110.155.47:8000/docs |



\> **\*\*Note:\*\*** The current backend uses a public HTTP endpoint for deployment/testing. HTTPS and a custom domain are future production-hardening improvements.



**---**



**# 🧭 Table of Contents**



\- [Project Objective]\(#-project-objective)

\- [End-to-End Workflow]\(#-end-to-end-workflow)

\- [Key Features]\(#-key-features)

\- [RFM Analysis]\(#-rfm-analysis)

\- [Machine Learning Workflow]\(#-machine-learning-workflow)

\- [Clustering Algorithms]\(#-clustering-algorithms)

\- [Customer Segments]\(#-customer-segments)

\- [Technology Stack]\(#-technology-stack)

\- [System Architecture]\(#-system-architecture)

\- [API Reference]\(#-api-reference)

\- [Repository Structure]\(#-repository-structure)

\- [Android Application]\(#-android-application)

\- [Getting Started]\(#-getting-started)

\- [Deployment]\(#-deployment)

\- [Project Status]\(#-project-status)

\- [Future Improvements]\(#-future-improvements)

\- [Disclaimer]\(#️-disclaimer)

\- [Developer]\(#-developer)



**---**



**# 🎯 Project Objective**



The objective was not only to build a clustering model, but to understand how an **\*\*unsupervised machine learning workflow can become a usable application\*\***.



\`\`\`text

Transaction Data

      ↓

Exploratory Data Analysis

      ↓

Data Cleaning

      ↓

RFM Feature Engineering

      ↓

Feature Scaling

      ↓

K-Means Clustering

      ↓

Cluster Evaluation

      ↓

Business Segment Interpretation

      ↓

Model Serialization

      ↓

FastAPI REST API

      ↓

AWS EC2 Deployment

      ↓

Streamlit Web Application

      ↓

Native Android Application

\`\`\`



\> **\*\*Machine Learning + Customer Analytics + API Development + Cloud Deployment + Web Development + Android Development\*\***



**---**



**# 🔄 End-to-End Workflow**



\`\`\`text

                    ┌──────────────────────┐

                    │ Transaction Dataset  │

                    └──────────┬───────────┘

                               ↓

                    ┌──────────────────────┐

                    │ EDA & Data Cleaning  │

                    └──────────┬───────────┘

                               ↓

                    ┌──────────────────────┐

                    │ RFM Feature          │

                    │ Engineering          │

                    └──────────┬───────────┘

                               ↓

                    ┌──────────────────────┐

                    │ StandardScaler       │

                    └──────────┬───────────┘

                               ↓

                    ┌──────────────────────┐

                    │ K-Means Clustering   │

                    │ Main Model           │

                    └──────────┬───────────┘

                               ↓

          ┌────────────────────┼────────────────────┐

          ↓                    ↓                    ↓

   Hierarchical             DBSCAN                 PCA

   Comparison               Analysis           Visualization

          └────────────────────┼────────────────────┘

                               ↓

                    ┌──────────────────────┐

                    │ Evaluation &         │

                    │ Business Insights    │

                    └──────────┬───────────┘

                               ↓

                    ┌──────────────────────┐

                    │ FastAPI REST API     │

                    │ POST /predict        │

                    └──────────┬───────────┘

                               ↓

                    ┌──────────┴──────────┐

                    ↓                     ↓

              Streamlit Web         Android App

\`\`\`



**---**



**# ✨ Key Features**



**## 🤖 Machine Learning**



\- RFM-based customer feature engineering.

\- Customer-level behavioral segmentation.

\- K-Means as the main production clustering algorithm.

\- Elbow/knee analysis for selecting a candidate K.

\- KneeLocator integration.

\- Silhouette Score for cluster evaluation.

\- Hierarchical Clustering comparison.

\- Dendrogram visualization.

\- DBSCAN analysis and noise detection.

\- PCA for dimensionality reduction and visualization.

\- StandardScaler for distance-based clustering.

\- Business-friendly segment labels.

\- Saved model/scaler artifacts for API inference.



**## ⚡ FastAPI REST API**



\- \`GET /\` health/status endpoint.

\- \`POST /predict\` prediction endpoint.

\- Pydantic request validation.

\- JSON request/response handling.

\- Saved ML artifact loading.

\- Uvicorn ASGI server.

\- Swagger/OpenAPI documentation.

\- AWS EC2 deployment.

\- systemd service.



**## 🌐 Streamlit Web Application**



The Streamlit application accepts **\*\*Recency, Frequency, and Monetary\*\*** values, calls the deployed FastAPI backend, and displays:



\- Customer cluster

\- Customer segment

\- RFM summary

\- Segment insights

\- Business recommendation

\- RFM visualization



**## 📱 Android Application**



The native Android application provides:



\- RFM input form

\- Customer segment prediction

\- Segment insights

\- Business recommendation

\- Loading state

\- API error handling

\- Settings screen

\- Dark Mode

\- Developer/contact information

\- Website and version information

\- Custom app icon



The Android application does **\*\*not\*\*** run the ML model locally. It communicates with the deployed FastAPI backend.



**---**



**# 📊 RFM Analysis**



RFM converts transaction-level behavior into customer-level features.



\| Metric | Meaning | General Interpretation |

\|---|---|---|

\| **\*\*Recency\*\*** | Days since the latest purchase | Lower is generally better |

\| **\*\*Frequency\*\*** | Number of purchases | Higher generally indicates stronger engagement |

\| **\*\*Monetary\*\*** | Total customer spending | Higher indicates greater monetary value |



\`\`\`text

Transaction Data

      ↓

CustomerID + InvoiceDate + TotalAmount

      ↓

RFM Aggregation

      ↓

Recency + Frequency + Monetary

      ↓

Customer-Level Dataset

\`\`\`



These features are scaled before distance-based clustering.



**---**



**# 🧠 Machine Learning Workflow**



**## 1. Exploratory Data Analysis**



The transaction dataset was explored for:



\- Structure and data types

\- Missing values

\- Duplicate records

\- Numerical distributions

\- Categorical distributions

\- Customer counts

\- Transaction dates

\- Spending behavior

\- Outliers and data quality



**## 2. Data Cleaning**



Cleaning includes:



\- Converting \`InvoiceDate\` to datetime.

\- Handling missing categorical values.

\- Removing duplicate transaction records.

\- Checking data types and numerical values.



**## 3. RFM Feature Engineering**



Transaction-level records are aggregated by customer to calculate Recency, Frequency, and Monetary values.



**## 4. Feature Scaling**



\`\`\`python

from sklearn.preprocessing import StandardScaler



scaler = StandardScaler()

X_scaled = scaler.fit_transform(X)

\`\`\`



Scaling is important because clustering algorithms based on distance can otherwise be dominated by features with larger numerical ranges.



**## 5. K-Means Clustering**



K-Means is the main clustering model used by the deployed prediction service.



\`\`\`text

Choose K

   ↓

Initialize Centroids

   ↓

Assign Customers

   ↓

Recalculate Centroids

   ↓

Repeat Until Convergence

   ↓

Customer Clusters

\`\`\`



**### K Selection**



The project evaluates inertia across different K values and uses \`KneeLocator\` to identify a candidate elbow:



\`\`\`python

from kneed import KneeLocator



kl = KneeLocator(

    range(1, 10),

    inertia,

    curve="convex",

    direction="decreasing"

)



optimal_k = kl.elbow

\`\`\`



**## 6. Cluster Evaluation**



Silhouette Score is used to evaluate cluster compactness and separation. It is treated as an evaluation signal rather than a universal pass/fail threshold.



**---**



**# 🎯 Clustering Algorithms**



**## K-Means**



The main production algorithm. It groups customers around learned centroids and requires a chosen number of clusters.



**\*\*Key concept:\*\*** Centroid 🎯



**## Hierarchical Clustering**



Agglomerative clustering is used for comparison. It starts with individual points and progressively merges clusters. A dendrogram visualizes the merge hierarchy.



**\*\*Key concept:\*\*** Dendrogram 🌳



**## DBSCAN**



A density-based method explored for irregular cluster structures and noise/outlier detection.



**\*\*Key concepts:\*\*** Core, Border, Noise (\`-1\`) 🔵🟡🔴



**## PCA**



Used for dimensionality reduction and visualization of the customer feature space. PCA is not the production prediction algorithm.



**---**



**# 👥 Customer Segments**



The generated clusters are interpreted using business-friendly segment labels.



**### 💎 High-Value / Loyal**



Strong purchasing activity and high monetary value.



**\*\*Recommendation:\*\*** retention, loyalty programs, premium offers, and personalized rewards.



**### 📈 Regular / Mid-Value**



Moderate purchasing activity and spending behavior.



**\*\*Recommendation:\*\*** personalized promotions, cross-selling, and engagement strategies.



**### 💤 Inactive / Low-Value**



Relatively low purchasing activity and/or lower monetary value.



**\*\*Recommendation:\*\*** re-engagement campaigns, targeted offers, and reactivation strategies.



\> Segment names are business interpretations of the generated clusters and should be validated against the underlying customer behavior.



**---**



# 🖼️ Screenshots & Project Assets

### 🌟 Feature Graphic

![Customer Intelligence Feature Graphic](assets/f1.png)

### 📱 Android Application Screenshots

#### Customer RFM Input

![Customer Intelligence Android - RFM Input](assets/s1.jpg)

#### Customer Segment Result

![Customer Intelligence Android - Segment Result](assets/s2.jpg)

#### Android Settings

![Customer Intelligence Android - Settings](assets/s3.jpg)

### 🌐 Streamlit Web Application

A PDF snapshot of the deployed Streamlit web application is included here:

[📄 View Streamlit Web Application PDF](assets/w1.pdf)

The PDF shows the RFM input, customer segment result, segment insights, business recommendation, and RFM visualization.

---

**# 🛠️ Technology Stack**



\| Layer | Technology |

\|---|---|

\| Programming | Python |

\| Data Analysis | Pandas |

\| Numerical Computing | NumPy |

\| Machine Learning | scikit-learn |

\| Main Clustering | K-Means |

\| Comparison Clustering | Hierarchical, DBSCAN |

\| Dimensionality Reduction | PCA |

\| Evaluation | Silhouette Score |

\| K Selection | KneeLocator |

\| Serialization | Joblib |

\| Backend | FastAPI |

\| API Server | Uvicorn |

\| Validation | Pydantic |

\| Web App | Streamlit |

\| HTTP Client | Requests |

\| Visualization | Matplotlib |

\| Android | Kotlin + Jetpack Compose |

\| Cloud | AWS EC2 |

\| Server OS | Ubuntu |

\| Service Manager | systemd |

\| Version Control | Git + GitHub |



**---**



**# 🏗️ System Architecture**



\`\`\`text

                              ┌────────────────────┐

                              │       User         │

                              └─────────┬──────────┘

                                        │

                         ┌──────────────┴──────────────┐

                         ↓                             ↓

              ┌─────────────────────┐       ┌─────────────────────┐

              │ Streamlit Web App   │       │ Native Android App  │

              └──────────┬──────────┘       └──────────┬──────────┘

                         │                             │

                         └──────────────┬──────────────┘

                                        ↓

                              ┌─────────────────────┐

                              │    FastAPI Backend  │

                              │      POST /predict  │

                              └──────────┬──────────┘

                                         ↓

                              ┌─────────────────────┐

                              │ RFM Processing +    │

                              │ Saved ML Artifacts  │

                              └──────────┬──────────┘

                                         ↓

                              ┌─────────────────────┐

                              │   K-Means Model     │

                              └──────────┬──────────┘

                                         ↓

                              ┌─────────────────────┐

                              │ Customer Segment    │

                              └─────────────────────┘

\`\`\`



**---**



**# ⚙️ API Reference**



**## \`GET /\`**



Checks whether the API is running.



\`\`\`text

http\://3.110.155.47:8000/

\`\`\`



**## \`POST /predict\`**



Accepts RFM information and returns a cluster and business segment.



**### Example Request**



\`\`\`json

{

  "recency": 30,

  "frequency": 10,

  "monetary": 50000

}

\`\`\`



**### Example Response**



\`\`\`json

{

  "cluster": 2,

  "segment": "Regular / Mid-Value"

}

\`\`\`



\> Exact results depend on the trained clustering artifacts and submitted values.



**### Swagger**



\`\`\`text

http\://3.110.155.47:8000/docs

\`\`\`



**---**



**# 📂 Repository Structure**



\`\`\`text

customer-intelligence-segmentation/

│

├── android-app/

│   └── CustomerIntelligence/

│       ├── app/

│       ├── gradle/

│       ├── build.gradle.kts

│       ├── gradle.properties

│       ├── gradlew

│       ├── gradlew\.bat

│       └── settings.gradle.kts

│

├── backend/

│   ├── main.py

│   └── schemas.py

│

├── data/

│   └── raw/

│

├── models/

│   ├── kmeans_model.joblib

│   └── scaler.joblib

│

├── notebooks/

│   └── customer_segmentation.ipynb

│

├── web-app/

│   └── app.py

│

├── .gitignore

├── README.md

└── requirements.txt

\`\`\`



**---**



**# 📱 Android Application**



**### Architecture**



\`\`\`text

User Input

    ↓

Jetpack Compose UI

    ↓

Customer RFM Data

    ↓

Retrofit / HTTP

    ↓

AWS FastAPI /predict

    ↓

Prediction Response

    ↓

Segment + Insights

\`\`\`



\`\`\`text

Application Name: Customer Intelligence

Package: com.sangamgupta.customerintelligence

Version: 1.0.0

\`\`\`



**---**



**# 🚀 Getting Started**



**## 1. Clone**



\`\`\`bash

git clone https\://github.com/TheSangamX/customer-intelligence-segmentation.git

cd customer-intelligence-segmentation

\`\`\`



**## 2. Virtual Environment**



**### Windows**



\`\`\`bash

python -m venv .venv

.venv\Scripts\activate

\`\`\`



**### Linux / macOS**



\`\`\`bash

python3 -m venv .venv

source .venv/bin/activate

\`\`\`



**## 3. Install Dependencies**



\`\`\`bash

pip install -r requirements.txt

\`\`\`



**## 4. Start FastAPI**



\`\`\`bash

python -m uvicorn backend.main:app --host 0.0.0.0 --port 8000

\`\`\`



Swagger:



\`\`\`text

http\://127.0.0.1:8000/docs

\`\`\`



**## 5. Run Streamlit**



\`\`\`bash

streamlit run web-app/app.py

\`\`\`



For the deployed web application, Streamlit communicates with the AWS-hosted FastAPI backend.



**## 6. Run Android**



1\. Open the Android project in Android Studio.

2\. Allow Gradle synchronization to complete.

3\. Ensure the required Android SDK is installed.

4\. Connect a device or start an emulator.

5\. Build and run the application.

6\. Use the deployed API base URL for cloud testing.



**---**



**# ☁️ Deployment**



The FastAPI backend was deployed on an **\*\*AWS EC2 Ubuntu server\*\***.



Deployment included:



1\. EC2 instance setup.

2\. Security group configuration.

3\. SSH access.

4\. Git repository cloning.

5\. Python virtual environment creation.

6\. Dependency installation.

7\. Uvicorn/FastAPI setup.

8\. Swagger/OpenAPI testing.

9\. \`systemd\` service configuration.

10\. Automatic service startup using \`systemctl enable\`.

11\. Port \`8000\` network access.

12\. Streamlit connection to the AWS API.

13\. Android connection to the same AWS API.



**### Deployment Architecture**



\`\`\`text

GitHub Repository

       ↓

AWS EC2

       ↓

Ubuntu Server

       ↓

Python Virtual Environment

       ↓

FastAPI + Uvicorn

       ↓

systemd

       ↓

K-Means Artifacts

       ↓

 ┌─────┴─────┐

 ↓           ↓

Streamlit   Android

\`\`\`



**---**



**# 🔒 Current Deployment Notes**



The current backend is exposed through:



\`\`\`text

http\://3.110.155.47:8000

\`\`\`



This is suitable for the current learning/deployment setup. For production hardening, consider:



\- HTTPS

\- Custom domain

\- API authentication

\- Nginx reverse proxy

\- Rate limiting

\- Restricted network access

\- Monitoring and logging

\- Stable public IP / Elastic IP



**---**



**# 📊 Project Status**



**## Completed**



\- [x] Transaction dataset

\- [x] Exploratory Data Analysis

\- [x] Missing-value handling

\- [x] Duplicate detection and removal

\- [x] Data cleaning

\- [x] RFM feature engineering

\- [x] Feature scaling

\- [x] K-Means clustering

\- [x] Elbow/knee analysis

\- [x] KneeLocator

\- [x] Silhouette Score evaluation

\- [x] Hierarchical Clustering comparison

\- [x] Dendrogram analysis

\- [x] DBSCAN analysis

\- [x] Noise/outlier analysis

\- [x] PCA analysis

\- [x] Business segment interpretation

\- [x] Saved ML artifacts

\- [x] FastAPI REST API

\- [x] Pydantic schema

\- [x] \`/predict\` endpoint

\- [x] Swagger/OpenAPI testing

\- [x] AWS EC2 deployment

\- [x] Uvicorn server

\- [x] systemd backend service

\- [x] Automatic service startup

\- [x] Streamlit web application

\- [x] Streamlit deployment

\- [x] Native Android application

\- [x] Kotlin + Jetpack Compose UI

\- [x] API integration

\- [x] Prediction flow

\- [x] Segment insights

\- [x] Business recommendation

\- [x] Settings screen

\- [x] Dark Mode

\- [x] GitHub repository
- [x] Project screenshots and feature graphic
- [x] Streamlit web application PDF
- [x] Google Play closed testing setup



**---**



**# 🔮 Future Improvements**



These are possible future improvements and are not claimed as currently implemented:



\- HTTPS-secured API

\- Custom backend domain

\- API authentication

\- Nginx reverse proxy

\- API rate limiting

\- Production monitoring and logging

\- Automated CI/CD

\- Expanded automated testing

\- Model versioning

\- Automated retraining

\- Prediction history

\- Customer analytics dashboard

\- Interactive cluster visualization

\- Retention/churn analysis

\- More advanced recommendation strategies



**---**



**# ⚠️ Disclaimer**



This project generates **\*\*customer segments based on RFM behavior and an unsupervised machine learning model\*\***.



The generated segment is an analytical representation of customer behavior and is not a guarantee of future customer actions. Business decisions should consider additional customer context, domain knowledge, and updated transaction data.



This project is intended for:



\- Educational purposes

\- Machine learning practice

\- Unsupervised learning practice

\- Customer analytics

\- API development practice

\- Cloud deployment learning

\- Full-stack ML application development



**---**



**# ⭐ What This Project Demonstrates**



This project demonstrates how an unsupervised machine learning workflow can move beyond a Jupyter Notebook and become part of a complete application ecosystem.



\`\`\`text

Transaction Dataset

        ↓

EDA

        ↓

Data Cleaning

        ↓

RFM Analysis

        ↓

Feature Scaling

        ↓

K-Means

        ↓

Cluster Evaluation

        ↓

Business Interpretation

        ↓

Saved ML Artifacts

        ↓

FastAPI

        ↓

AWS EC2

        ↓

Streamlit Web App

        +

Native Android App

\`\`\`



The same central prediction service is consumed by multiple client applications, demonstrating the transition from **\*\*machine learning experimentation to a deployed end-to-end ML application\*\***.



**---**



**# 👨‍💻 Developer**



**\*\*Sangam Gupta\*\***



\- 🌐 Website: https\://sangamgupta.in

\- 📧 Email: contact\@sangamgupta.in

\- 💻 GitHub: https\://github.com/TheSangamX



**---**



**## ⭐ If you found this project interesting**



Consider giving the repository a star. It documents the complete journey from **\*\*customer transaction data and unsupervised machine learning to FastAPI, AWS deployment, Streamlit, and Android integration\*\***.
