package org.acquiring.service.acquirings.yookassa;

import org.acquiring.service.acquirings.yookassa.dto.YookassaPayPageRequest;
import org.acquiring.service.acquirings.yookassa.dto.YookassaPayPageResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.PostExchange;

public interface YookassaRestClient {
    @PostExchange(url = "/payments")
    YookassaPayPageResponse postPayPage(
            @RequestHeader("Authorization") String authorization,
            @RequestHeader("Idempotence-Key") String iKey,
            @RequestBody YookassaPayPageRequest request);
}
