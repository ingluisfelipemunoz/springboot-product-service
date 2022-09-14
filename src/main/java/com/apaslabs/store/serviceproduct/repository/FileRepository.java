package com.apaslabs.store.serviceproduct.repository;

import com.apaslabs.store.serviceproduct.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findFileByType(String type);
}
