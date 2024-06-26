package com.lizhongbin.ch_final.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordManager {
    /**
     * 使用BCrypt对密码进行哈希处理。
     * @param password 明文密码
     * @return 加密后的密码哈希值
     */
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * 验证给定的明文密码是否与存储的哈希密码匹配。
     * @param rawPassword 用户输入的明文密码
     * @param encodedPassword 数据库中存储的哈希密码
     * @return 密码是否匹配
     */
    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}