package org.acquiring.service.acquirings.yookassa;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.acquiring.service.acquirings.yookassa.dto.YookassaNotification;
import org.acquiring.service.service.PayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/acquiring/callback/v1/yookassa")
@RequiredArgsConstructor
@Slf4j
public class YookassaRestController {

    private final PayService payService;

    @PostMapping("/notification")
    public ResponseEntity<?> getCallback(@RequestBody YookassaNotification request){
        try {
            payService.callbackYookassaPayHandle(request);
        }
        catch (Exception e){
            log.error("Callback exception: " , e);
        }

        return ResponseEntity.ok().build();
    }
}
