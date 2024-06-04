package com.example.demo_updated.demo_updated.config;

import com.example.demo_updated.demo_updated.model.Employee;


import com.example.demo_updated.demo_updated.repository.EmployeeRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Bean
    public FlatFileItemReader<Employee> reader() {

        FlatFileItemReader<Employee> reader= new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("/employee.csv"));

        reader.setLineMapper(new DefaultLineMapper<Employee>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setDelimiter(DELIMITER_COMMA);
                setNames("first_name","middle_name","last_name","dob","gender","role","username","password");
            }});

            setFieldSetMapper(new BeanWrapperFieldSetMapper<Employee>() {{
                setTargetType(Employee.class);
            }});
        }});

        reader.setRecordSeparatorPolicy(new BlankLineRecordSeparatorPolicy());

        return reader;
    }

    @Autowired
    private EmployeeRepository repository;

    //Writer class Object
    @Bean
    public ItemWriter<Employee> writer(){
        return emp -> {
            repository.saveAll(emp);
        };
    }

    @Bean
    public ItemProcessor<Employee, Employee> processor(){
        return emp -> {
            return emp;
        };
    }


    @Bean
    public JobExecutionListener listener() {
        return new EmployeeListener();
    }
    @Autowired
    private StepBuilderFactory sbf;

    //Step Object
    @Bean
    public Step stepA() {
        return sbf.get("stepA")
                .<Employee,Employee>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build()
                ;
    }

    @Autowired
    private JobBuilderFactory jbf;

    //Job Object
    @Bean
    public Job jobA(){
        return jbf.get("jobA")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .start(stepA())
                .build();
    }
}
