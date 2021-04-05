package vrielynckpieterjan.applicationlayer.revocation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RevocationObjectTest {

    @Test
    void isValid() {
        RevocationSecret revocationSecret = new RevocationSecret();
        RevocationCommitment revocationCommitment = new RevocationCommitment(revocationSecret);
        RevocationObject revocationObject = new RevocationObject(revocationCommitment, revocationSecret);
        assertTrue(revocationObject.isValid());

        RevocationObject invalidRevocationObject = new RevocationObject(revocationCommitment, new RevocationSecret("test"));
        assertFalse(invalidRevocationObject.isValid());
    }
}