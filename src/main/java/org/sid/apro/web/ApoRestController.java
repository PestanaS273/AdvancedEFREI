package org.sid.apro.web;

import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@CrossOrigin("*")
public class ApoRestController {
}
