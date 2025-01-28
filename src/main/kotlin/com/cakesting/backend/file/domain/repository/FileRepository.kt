package com.cakesting.backend.file.domain.repository

import com.cakesting.backend.file.domain.File
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<File, Long> {
}