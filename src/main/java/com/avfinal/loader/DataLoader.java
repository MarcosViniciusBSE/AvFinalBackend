package com.avfinal.loader;
import com.avfinal.model.UserRole;
import com.avfinal.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            UserRole adminRole = new UserRole();
            adminRole.setName("Admin");
            UserRole funcionarioRole = new UserRole();
            funcionarioRole.setName("Funcionario");
            UserRole clienteRole = new UserRole();
            clienteRole.setName("Cliente");

            roleRepository.save(adminRole);
            roleRepository.save(funcionarioRole);
            roleRepository.save(clienteRole);

            System.out.println("Dados padrão carregados no banco!");
        } else {
            System.out.println("Banco de dados já contém dados.");
        }
    }
}

