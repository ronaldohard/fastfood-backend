package br.com.fiap.postech.grupo5.fastfood.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificarClienteService {

    public void enviarSMS(Long pedidoId, String celular) {

        log.info("Seu pedido #{} esta pronto!", pedidoId);
        log.info("Enviando SMS para Celular", celular);

        //todo - SnsClient -> AWS -> Lambda -> SNS

    }

}
