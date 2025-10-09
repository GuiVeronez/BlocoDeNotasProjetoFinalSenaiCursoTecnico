# Usa imagem base do Java 17
FROM eclipse-temurin:17-jdk

# Cria o diretório de trabalho
WORKDIR /app

# Copia o arquivo pom.xml e baixa dependências
COPY pom.xml .
RUN ./mvnw dependency:go-offline

# Copia o restante do código
COPY . .

# Empacota a aplicação
RUN ./mvnw package -DskipTests

# Expõe a porta do Spring Boot
EXPOSE 8080

# Roda o .jar gerado
CMD ["java", "-jar", "target/*.jar"]
