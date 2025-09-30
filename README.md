# AMRIT - Early Childhood Developemnt (ECD) Service

[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
![Build Status](https://github.com/PSMRI/ECD-API/actions/workflows/sast-and-package.yml/badge.svg)

The Early Childhood Development (ECD) Initiative by the Ministry of Health and Family Welfare (MoHFW) aims to nurture the cognitive capital of the country by enabling young children to attain their fullest potential. The initiative focuses on the critical period of brain development, which includes the 270 days of pregnancy and the first two years of the child's life, also known as the first 1,000 days.

## Features of Early Childhood Developemnt (ECD)

- **Focus on the first 1,000 days:** Recognize the critical period of brain development and emphasize the importance of interventions during this period to promote physical, cognitive, social, and emotional development.

- **Communicate with mothers and families:** Acknowledge the vital role of mothers in the holistic development of their children. Develop simple, effective, and personalized messages to communicate with mothers and other family members, highlighting their significant impact on the child's overall development.

- **Reinforce messages through multiple channels:** Establish a unified approach by involving other health providers like ASHA, ANM, and medical officers to reiterate the same messages to caregivers. Consistent information is essential to ensure that parents receive comprehensive guidance.

- **Provide credible advice:** Train resources in the field of early childhood development to deliver information and counseling through the ECD Call Centre. The trained personnel will provide credible advice, enhancing the caregivers' trust in the information provided.

- **Offer personalized advice:** Utilize the interactive platform of the ECD Call Centre to provide personalized advice to parents based on their specific needs and their child's requirements. Building on existing positive practices, suggest additional actions that caregivers can take to promote their child's development.

- **Enhance caregivers' knowledge:** Emphasize the importance of caregivers' knowledge about early childhood development. Provide them with information and resources to better understand their child's developmental needs and milestones.

- **Boost caregivers' confidence:** Empower caregivers to feel confident in their abilities to support their child's development. Encourage them to take simple but meaningful actions that contribute positively to their child's growth.

- **Collaborate with other initiatives:** Complement existing initiatives such as LaQshya, Homebased Care for Young Child, and the Comprehensive New Born Screening under RBSK. By working collaboratively, ensure a comprehensive and integrated approach to early childhood development.

- **Foster a nature-friendly environment:** Promote the importance of creating a stimulating, loving, and protective care environment for children. Encourage activities that connect children with nature and promote their overall well-being.

- **Continuously evaluate and improve:** Regularly assess the effectiveness of the ECD initiative and the ECD Call Centre. Seek feedback from parents and caregivers to identify areas for improvement and refine the messages and services provided.

## Building From Source

This microservice is built using Java and the Spring Boot framework, with MySQL as the underlying database. Before building the ECD module, ensure you have the following prerequisites:
- JDK 17
- Maven
- Redis
- Spring Boot V2
- MySQL
For steps to clone and set up this Repository, refer to the [Developer Guide](https://piramal-swasthya.gitbook.io/amrit/developer-guide/development-environment-setup)
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

## Setting Up Commit Hooks

This project uses Git hooks to enforce consistent code quality and commit message standards. Even though this is a Java project, the hooks are powered by Node.js. Follow these steps to set up the hooks locally:

### Prerequisites
- Node.js (v14 or later)
- npm (comes with Node.js)

### Setup Steps

1. **Install Node.js and npm**
   - Download and install from [nodejs.org](https://nodejs.org/)
   - Verify installation with:
     ```
     node --version
     npm --version
     ```
2. **Install dependencies**
   - From the project root directory, run:
     ```
     npm ci
     ```
   - This will install all required dependencies including Husky and commitlint
3. **Verify hooks installation**
   - The hooks should be automatically installed by Husky
   - You can verify by checking if the `.husky` directory contains executable hooks
### Commit Message Convention
This project follows a specific commit message format:
- Format: `type(scope): subject`
- Example: `feat(login): add remember me functionality`
Types include:
- `feat`: A new feature
- `fix`: A bug fix
- `docs`: Documentation changes
- `style`: Code style changes (formatting, etc.)
- `refactor`: Code changes that neither fix bugs nor add features
- `perf`: Performance improvements
- `test`: Adding or fixing tests
- `build`: Changes to build process or tools
- `ci`: Changes to CI configuration
- `chore`: Other changes (e.g., maintenance tasks, dependencies)
Your commit messages will be automatically validated when you commit, ensuring project consistency.

## Filing Issues

If you encounter any issues, bugs, or have feature requests, please file them in the [main AMRIT repository](https://github.com/PSMRI/AMRIT/issues). Centralizing all feedback helps us streamline improvements and address concerns efficiently.  

## Join Our Community

We’d love to have you join our community discussions and get real-time support!  
Join our [Discord server](https://discord.gg/FVQWsf5ENS) to connect with contributors, ask questions, and stay updated.  

## Usage

All the features of the ECD module have been exposed as REST endpoints. For detailed information on how to use the service, refer to the SWAGGER API specification.

With the ECD module, you can efficiently manage all aspects of your telemedicine application, ensuring seamless remote healthcare services for patients and collaboration among healthcare professionals.
