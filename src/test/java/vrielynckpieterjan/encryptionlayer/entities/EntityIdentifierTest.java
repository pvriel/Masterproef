package vrielynckpieterjan.encryptionlayer.entities;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import vrielynckpieterjan.applicationlayer.attestation.policy.PolicyRight;
import vrielynckpieterjan.applicationlayer.attestation.policy.RTreePolicy;
import vrielynckpieterjan.encryptionlayer.schemes.IBEDecryptableSegment;
import vrielynckpieterjan.encryptionlayer.schemes.RSACipherEncryptedSegment;
import vrielynckpieterjan.encryptionlayer.schemes.WIBEDecryptableSegment;

import static org.junit.jupiter.api.Assertions.*;

class EntityIdentifierTest {

    Pair<PrivateEntityIdentifier, PublicEntityIdentifier> entityIdentifierPair = EntityIdentifier.generateEntityIdentifierPair("");
    String data = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut " +
            "labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi " +
            "ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse " +
            "cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa " +
            "qui officia deserunt mollit anim id est laborum.";

    @Test
    void RSAIdentifierTest() {
        RSACipherEncryptedSegment<String> encryptedSegment = new RSACipherEncryptedSegment<>(data, entityIdentifierPair.getRight());
        String decrypted = encryptedSegment.decrypt(entityIdentifierPair.getLeft());
        assertEquals(data, decrypted);
    }

    @Test
    void IBEIdentifierTest() {
        String usedIBEIdentifier = "WRITE://A/B/C";
        IBEDecryptableSegment<String> decryptableSegment = new IBEDecryptableSegment<>(data, entityIdentifierPair.getRight(), usedIBEIdentifier);
        String decrypted = decryptableSegment.decrypt(entityIdentifierPair.getLeft(), usedIBEIdentifier);
        assertEquals(data, decrypted);
    }

    @Test
    void WIBEIdentifierTest() {
        RTreePolicy usedWIBEIdentifier = new RTreePolicy(PolicyRight.WRITE, "A", "B");
        WIBEDecryptableSegment<String>  decryptableSegment = new WIBEDecryptableSegment<>(data, entityIdentifierPair.getRight(), usedWIBEIdentifier);
        String decrypted = decryptableSegment.decrypt(entityIdentifierPair.getLeft(), usedWIBEIdentifier);
        assertEquals(data, decrypted);
    }
}