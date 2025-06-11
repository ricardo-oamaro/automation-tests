package br.com.test.hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import org.junit.Before;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ApiHooks {

    private static final String DB_PATH = "db.json";
    private static final String BACKUP_DB_PATH = "db_backup.json";

    @Before
    public void backupDb() {
        // Fazendo a cópia do db.json para o db-backup.json antes de cada teste
        try {
            Files.copy(Paths.get(DB_PATH), Paths.get(BACKUP_DB_PATH), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Backup do db.json realizado antes do teste.");
        } catch (IOException e) {
            System.err.println("Erro ao realizar backup do db.json: " + e.getMessage());
        }
    }

    @After
    public void restoreDb() {
        System.out.println("Executando restoreDb...");
        try {
            Files.copy(Paths.get(BACKUP_DB_PATH), Paths.get(DB_PATH), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("db.json restaurado após o teste.");
        } catch (IOException e) {
            System.err.println("Erro ao restaurar o db.json: " + e.getMessage());
        }
    }
}
