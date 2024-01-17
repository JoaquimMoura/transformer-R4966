package br.com.poupex.starters.api.commons.helpers;

import lombok.Getter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;
@Getter
public class PexPrincipal {

	private final String cpf;
	private final String nome;
	private final Optional<String> matricula;
	private final Optional<String> email;
	private final Optional<String> ramal;
	private final Optional<String> uta;
	private final Optional<Integer> utaId;

	public static PexPrincipal of(Principal principal) {
		return new PexPrincipal(principal);
	}

	private PexPrincipal(Principal principal) {
		var claims = principal instanceof JwtAuthenticationToken ? ((JwtAuthenticationToken) principal).getTokenAttributes() : Map.<String, Object>of();
		cpf = getStringClaim(claims, "cpf");
		nome = getStringClaim(claims, "nome");
		matricula = getOptionalStringClaim(claims, "matricula");
		email = getOptionalStringClaim(claims, "mail");
		ramal = getOptionalStringClaim(claims, "ramal");
		uta = getOptionalStringClaim(claims, "uta");
		utaId = getOptionalIntegerClaim(claims, "utaid");
	}

	private String getStringClaim(Map<String, Object> claims, String claim) {
		return claims.get(claim).toString();
	}

	private Optional<String> getOptionalStringClaim(Map<String, Object> claims, String claim) {
		return Optional.ofNullable(claims.get(claim)).filter(c -> !c.toString().equalsIgnoreCase("null")).map(Object::toString);
	}

	private Optional<Integer> getOptionalIntegerClaim(Map<String, Object> claims, String claim) {
		return this.getOptionalStringClaim(claims, claim).map(Integer::parseInt);
	}

}
