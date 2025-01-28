package com.cakesting.backend.owner.domain.repository

import com.cakesting.backend.owner.domain.Market
import org.springframework.data.jpa.repository.JpaRepository

interface MarketRepository : JpaRepository<Market, Long> {
}