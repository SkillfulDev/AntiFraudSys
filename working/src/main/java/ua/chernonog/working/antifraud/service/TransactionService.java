package ua.chernonog.working.antifraud.service;

import org.springframework.stereotype.Service;
import ua.chernonog.working.antifraud.model.enumreq.Permission;

@Service
public class TransactionService {
    public Enum<Permission> getResultOfPermission(int amount) {
        if (amount >= 150) {
            return Permission.ALLOWED;
        }
        return Permission.MANUAL_PROCESSING;
    }

}
