package kau.DIBN.nft;

public class WalletDTO {
        private String keystore;
        private String passwd;

        public void setKeystore(String keystore) {
            this.keystore = keystore;
        }
        public String getKeystore() {
            return keystore;
        }
        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }
        public String getPasswd() {
            return passwd;
        }
}
