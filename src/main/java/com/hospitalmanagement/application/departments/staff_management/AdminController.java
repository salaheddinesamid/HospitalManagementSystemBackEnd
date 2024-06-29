package com.hospitalmanagement.application.departments.staff_management;

import com.hospitalmanagement.application.dto.LoginDto;
import com.hospitalmanagement.application.dto.RegisterDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class AdminController {
    private final AdminService adminService;
    private final JwtUtilities jwtUtilities;
    private final AdminRepository adminRepository;
    @GetMapping("/test")
    public String test(){
        return "Hello";
    }
    @PostMapping("/register")
    public ResponseEntity<?> register (@RequestBody RegisterDto registerDto) {
        return adminService.register(registerDto);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody LoginDto loginDto) {
        return adminService.authenticate(loginDto);
    }
    @GetMapping("/getadmin")
    public Admin getAdminDetails(@RequestHeader("Authorization") String token){
        String email = jwtUtilities.extractUserName(token.substring(7));
        return adminService.getDetails(email);
    }

    @PutMapping("settings/{email}")

    public void editAdminDetails(@PathVariable String email, @RequestBody AdminDto adminDto){
        adminService.editAdmin(email,adminDto);
    }
}
