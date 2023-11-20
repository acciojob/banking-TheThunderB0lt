package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public String getTradeLicenceID() {
        return tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000.0);
        this.tradeLicenseId = tradeLicenseId;
        if(balance < 5000) {
            throw new Exception("Insufficient Balance");
        }

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        if(!isValid(tradeLicenseId)) {
            String rearrange = RearrangeString(tradeLicenseId);

            if(rearrange == "") {
                throw new Exception("Valid License can not be generated");
            } else {
                this.tradeLicenseId = rearrange;
            }
        }
    }

    public String RearrangeString(String id) {
        int n = id.length();
        int[] cnt = new int[26];

        for(int i = 0; i < 26; i++) {
            cnt[i] = 0;
        }

        for(char c : id.toCharArray()) {
            cnt[(int)c-(int)'A']++;
        }

        char c_max = getCharCnt(cnt);
        int max_cnt = cnt[(int)c_max-(int)'A'];

        if(max_cnt > (n+1)/2) {
            return "";
        }

        String res = "";
        for (int i = 0; i < n; i++) {
            res += ' ';
        }

        int idx = 0;
        while(max_cnt > 0) {
            res = res.substring(0, idx) + c_max + res.substring(idx + 1); 
            idx += 2;
            max_cnt--;
        }
        cnt[(int)c_max-(int)'A'] = 0;
        for(int i = 0; i < 26; i++) {
            while(cnt[i] > 0) {
                idx = (idx >= 1) ? 1 : idx;
                res = res.substring(0, idx) + (char)((int)'A' + i) + res.substring(idx + 1); 
                idx += 2;
                cnt[i]--;
            }
        }
        return res;
    }

    public char getCharCnt(int[] cnt) {
        int maxi = 0;
        char ch = 0;

        for (int i = 0; i < 26; i++) {
            if(cnt[i] > maxi) {
                maxi = cnt[i];
                ch = (char)((int)'A' + i);
            }
        }
        return ch;
    }

    public boolean isValid(String id) {
        if(id.length() == 0) return false;

        for(int i = 0; i < id.length()-1; i++) {
            if(id.charAt(i) == id.charAt(+1)){
                return false;
            } 
        }
        return true;
    }

}
