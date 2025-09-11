package inc.encora.FlightSearch.amadeus.constants.common;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

import static org.assertj.core.api.Assertions.assertThat;

class AmadeusWebClientTypeTest {

    @Test
    void shouldHaveCorrectNoCredentialsConstant() {
        assertThat(AmadeusWebClientType.NO_CREDENTIALS)
                .isEqualTo("amadeusWebClient");
    }

    @Test
    void shouldHaveCorrectWithCredentialsConstant() {
        assertThat(AmadeusWebClientType.WITH_CREDENTIALS)
                .isEqualTo("amadeusWebClientWithCredentials");
    }

    @Test
    void shouldHaveAllConstantsAsPublicStaticFinal() throws NoSuchFieldException {
        var noCredentialsField = AmadeusWebClientType.class.getField("NO_CREDENTIALS");
        var withCredentialsField = AmadeusWebClientType.class.getField("WITH_CREDENTIALS");

        assertThat(Modifier.isPublic(noCredentialsField.getModifiers())).isTrue();
        assertThat(Modifier.isStatic(noCredentialsField.getModifiers())).isTrue();
        assertThat(Modifier.isFinal(noCredentialsField.getModifiers())).isTrue();

        assertThat(Modifier.isPublic(withCredentialsField.getModifiers())).isTrue();
        assertThat(Modifier.isStatic(withCredentialsField.getModifiers())).isTrue();
        assertThat(Modifier.isFinal(withCredentialsField.getModifiers())).isTrue();
    }

    @Test
    void shouldHaveClassAsFinal() {
        assertThat(Modifier.isFinal(AmadeusWebClientType.class.getModifiers())).isTrue();
    }

    @Test
    void shouldBeInstantiable() throws NoSuchMethodException, ReflectiveOperationException {
        Constructor<AmadeusWebClientType> constructor = AmadeusWebClientType.class.getDeclaredConstructor();
        AmadeusWebClientType instance = constructor.newInstance();
        assertThat(instance).isNotNull();
    }

    @Test
    void shouldHavePublicDefaultConstructor() throws NoSuchMethodException {
        Constructor<AmadeusWebClientType> constructor = AmadeusWebClientType.class.getDeclaredConstructor();
        assertThat(Modifier.isPublic(constructor.getModifiers())).isTrue();
    }

    @Test
    void shouldHaveOnlyTwoConstants() {
        var fields = AmadeusWebClientType.class.getFields();
        assertThat(fields).hasSize(2);
    }
}
