# AMRIT - Early Childhood Developemnt (ECD) Service

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
![Build Status](https://github.com/PSMRI/ECD-API/actions/workflows/sast-and-package.yml/badge.svg)

The Early Childhood Development (ECD) Initiative by the Ministry of Health and Family Welfare (MoHFW) aims to nurture the cognitive capital of the country by enabling young children to attain their fullest potential. The initiative focuses on the critical period of brain development, which includes the 270 days of pregnancy and the first two years of the child's life, also known as the first 1,000 days.

## Features of Early Childhood Developemnt (ECD)

- **Prioritize the First 1,000 Days:** Support brain and holistic development during this critical window.
- **Engage Families:** Share simple, impactful messages with mothers and caregivers.
- **Consistent Messaging:** Reinforce key messages through ASHAs, ANMs, and medical officers.
- **Credible Guidance:** Train ECD experts to offer trusted advice via the Call Centre.
- **Personalized Support:** Tailor advice to each child's unique needs through interactive calls.
- **Knowledge Empowerment:** Provide caregivers with information on milestones and child development.
- **Build Confidence:** Encourage caregivers to take meaningful actions with assurance.
- **Synergize Efforts:** Align with programs like LaQshya, HBYC, and RBSK.
- **Nature & Nurture:** Promote safe, loving, and nature-connected environments.
- **Improve Continuously:** Collect feedback and refine services for better impact.

---

## Building From Source

This microservice is built using Java and the Spring Boot framework, with MySQL as the underlying database. Before building the ECD module, ensure you have the following prerequisites:
For steps to clone and set up this Repository, refer to the [Developer Guide](https://piramal-swasthya.gitbook.io/amrit/developer-guide/development-environment-setup)
- JDK 17
- Maven
- Redis
- Spring Boot V2
- MySQL

To build the ECD module from source, follow these steps:

1. Clone the repository to your local machine.
2. Install the required dependencies and build the module using the following command:
- Execute the following command:
  ```
  mvn clean install
  ```
3. You can copy `ecd_example.properties` to `ecd_local.properties` and edit the file accordingly. The file is under `src/main/environment` folder.
4. Run the spring server with local configuration `mvn spring-boot:run -DENV_VAR=local` 

- Open your browser and navigate to http://localhost:8080/swagger-ui.html#!/

## Filing Issues

If you encounter any issues, bugs, or have feature requests, please file them in the [main AMRIT repository](https://github.com/PSMRI/AMRIT/issues). Centralizing all feedback helps us streamline improvements and address concerns efficiently.  

## Join Our Community

We’d love to have you join our community discussions and get real-time support!  
Join our [Discord server](https://discord.gg/FVQWsf5ENS) to connect with contributors, ask questions, and stay updated.  

## Usage

All the features of the ECD module have been exposed as REST endpoints. For detailed information on how to use the service, refer to the SWAGGER API specification.

With the ECD module, you can efficiently manage all aspects of your telemedicine application, ensuring seamless remote healthcare services for patients and collaboration among healthcare professionals.
