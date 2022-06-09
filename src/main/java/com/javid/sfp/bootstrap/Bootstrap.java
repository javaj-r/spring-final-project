package com.javid.sfp.bootstrap;

import com.javid.sfp.model.Admin;
import com.javid.sfp.model.Work;
import com.javid.sfp.model.Workgroup;
import com.javid.sfp.repository.AdminRepository;
import com.javid.sfp.repository.UserRepository;
import com.javid.sfp.repository.WorkRepository;
import com.javid.sfp.repository.WorkgroupRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;

/**
 * @author javid
 * Created on 5/12/2022
 */
@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    public static final String APPLIANCE = "Appliance";
    public static final String CLEANING_AND_HYGIENE = "Cleaning-And-Hygiene";
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final WorkgroupRepository workgroupRepository;
    private final WorkRepository workRepository;
    private final PasswordEncoder passwordEncoder;

    public Bootstrap(UserRepository userRepository, AdminRepository adminRepository,
                     WorkgroupRepository workgroupRepository, WorkRepository workRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.workgroupRepository = workgroupRepository;
        this.workRepository = workRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        insertAdmin("admin@app.com", "Admin$123", "No-Firstname", "No-Lastname");

        insertWorkgroups(APPLIANCE,
                CLEANING_AND_HYGIENE,
                "Building-Decoration",
                "Building-Facilities",
                "Vehicles",
                "Moving");
        insertApplianceWorks();
        insertCleaningAndHygieneWorks();

        log.info("\nDefault admin user :\n"
                + "\tEmail (Username) : admin@app.com\n"
                + "\tPassword : Admin$123\n"
        );
    }

    public void insertAdmin(String email, String password, String firstname, String lastname) {
        if (userRepository.existsByEmail(email)) {
            return;
        }
        var encodedPassword = passwordEncoder.encode(password);

        log.info("Password length: " + encodedPassword.length());

        var admin = new Admin();
        admin.setEmail(email);
        admin.setPassword(encodedPassword);
        admin.setFirstname(firstname);
        admin.setLastname(lastname);
        admin.setEnabled(true);
        admin.getRoles().add("ROLE_ADMIN");
        try {
            adminRepository.saveAndFlush(admin);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    public void insertWorkgroups(String... names) {
        var workgroups = new HashSet<Workgroup>();
        for (var name : names) {
            if (workgroupRepository.findByName(name).isPresent()) {
                continue;
            }
            var workgroup = new Workgroup();
            workgroup.setName(name);
            workgroups.add(workgroup);
        }
        try {
            workgroupRepository.saveAllAndFlush(workgroups);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    private void insertApplianceWorks() {
        insertWork(APPLIANCE,
                "Kitchenware",
                "Some descriptions for Kitchenware",
                500000L);

        insertWork(APPLIANCE,
                "Washing-Machine",
                "Some descriptions for Washing-Machine",
                200000L);

        insertWork(APPLIANCE,
                "Media-Equipment",
                "Some descriptions for Media-Equipment",
                800000L);
    }

    private void insertCleaningAndHygieneWorks() {
        insertWork(CLEANING_AND_HYGIENE,
                "cleaning",
                "Some descriptions for cleaning",
                50000L);

        insertWork(CLEANING_AND_HYGIENE,
                "laundry",
                "Some descriptions for laundry",
                60000L);

        insertWork(CLEANING_AND_HYGIENE,
                "carpet-and-sofa-washing",
                "Some descriptions for carpet-and-sofa-washing",
                900000L);

        insertWork(CLEANING_AND_HYGIENE,
                "home-spraying",
                "Some descriptions for home-spraying",
                700000L);
    }

    @Transactional
    public void insertWork(String workgroupName, String name, String description, Long basePrice) {
        if (workRepository.findByName(name).isPresent()) {
            return;
        }
        var work = new Work();
        work.setName(name);
        work.setDescription(description);
        work.setBasePrice(new BigDecimal(basePrice));

        workgroupRepository.findByName(workgroupName)
                .ifPresent(workgroup -> {
                    try {
                        work.setWorkgroup(workgroup);
                        workRepository.saveAndFlush(work);
                    } catch (Exception e) {
                        log.debug(e.getMessage());
                    }
                });
    }

}
