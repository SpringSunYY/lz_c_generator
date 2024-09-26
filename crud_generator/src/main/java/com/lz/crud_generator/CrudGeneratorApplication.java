package com.lz.crud_generator;

import com.lz.crud_generator.mapper.GenTableColumnMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication()
public class CrudGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudGeneratorApplication.class, args);
    }

}
