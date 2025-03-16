package com.aivestor.Investor_backend.Controller;

import com.aivestor.Investor_backend.Service.KisApiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/kis")
public class KisApiController {
    private final KisApiService kisApiService;

//    @GetMapping("/stock-price/{stockCode}")
//    public Map<String, Object> getStockPrice(@PathVariable String stockCode) {
//        return kisApiService.getNasdaqStockPrice(stockCode);
//    }
}

