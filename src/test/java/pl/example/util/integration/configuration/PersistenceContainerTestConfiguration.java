package pl.example.util.integration.configuration;

//@TestConfiguration
public class PersistenceContainerTestConfiguration {

//    private static final String USERNAME = "postgres";
//    private static final String PASSWORD = "postgres";
//    private static final String POSTGRESQL = "postgresql";
//    private static final String POSTGRESQL_CONTAINER = "postgres:15.0";
//
//    @Bean
//    @Qualifier(POSTGRESQL)
//    PostgreSQLContainer<?> postgresqlContainer() {
//        PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>(POSTGRESQL_CONTAINER)
//                .withUsername(USERNAME)
//                .withPassword(PASSWORD);
//        postgresqlContainer.start();
//        System.setProperty("spring.datasource.username", postgresqlContainer.getUsername());
//        System.setProperty("spring.datasource.password", postgresqlContainer.getPassword());
//        return postgresqlContainer;
//    }
//
//    @Bean
//    DataSource dataSource(final PostgreSQLContainer<?> container) {
//        return DataSourceBuilder.create()
//                .type(HikariDataSource.class)
//                .driverClassName(container.getDriverClassName())
//                .url(container.getJdbcUrl())
//                .username(container.getUsername())
//                .password(container.getPassword())
//                .build();
//    }
}
