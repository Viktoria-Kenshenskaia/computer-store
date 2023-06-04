package com.computer.store.initializers;

import com.computer.store.entities.Desktop;
import com.computer.store.entities.DesktopFormFactor;
import com.computer.store.entities.HardDisk;
import com.computer.store.entities.Laptop;
import com.computer.store.entities.LaptopSize;
import com.computer.store.entities.Monitor;
import com.computer.store.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository repository;

    @Override
    public void run(String... args) throws Exception {
        Monitor monitor1 = new Monitor();
        Monitor monitor2 = new Monitor();

        monitor1.setPrice(BigDecimal.valueOf(149.9));
        monitor1.setQuantityInStock(10);
        monitor1.setDiagonal((float) 52.0);
        monitor2.setPrice(BigDecimal.valueOf(249.9));
        monitor2.setQuantityInStock(5);
        monitor2.setDiagonal((float) 100.0);

        repository.save(monitor1);
        repository.save(monitor2);

        Laptop laptop1 = new Laptop();
        Laptop laptop2 = new Laptop();

        laptop1.setPrice(BigDecimal.valueOf(149.9));
        laptop1.setQuantityInStock(10);
        laptop1.setLaptopSize(LaptopSize.INCH_17);
        laptop2.setPrice(BigDecimal.valueOf(159.9));
        laptop2.setQuantityInStock(20);
        laptop2.setLaptopSize(LaptopSize.INCH_13);

        repository.save(laptop1);
        repository.save(laptop2);

        Desktop desktop1 = new Desktop();
        Desktop desktop2 = new Desktop();

        desktop1.setPrice(BigDecimal.valueOf(249.9));
        desktop1.setQuantityInStock(10);
        desktop1.setFormFactor(DesktopFormFactor.MONOBLOCK);
        desktop2.setPrice(BigDecimal.valueOf(349.9));
        desktop2.setQuantityInStock(22);
        desktop2.setFormFactor(DesktopFormFactor.NETTOP);

        repository.save(desktop1);
        repository.save(desktop2);

        HardDisk hardDisk1 = new HardDisk();
        HardDisk hardDisk2 = new HardDisk();

        hardDisk1.setPrice(BigDecimal.valueOf(49.9));
        hardDisk1.setQuantityInStock(10);
        hardDisk1.setCapacity(2000);
        hardDisk2.setPrice(BigDecimal.valueOf(39.9));
        hardDisk2.setQuantityInStock(10);
        hardDisk2.setCapacity(500);

        repository.save(hardDisk1);
        repository.save(hardDisk2);
    }
}
