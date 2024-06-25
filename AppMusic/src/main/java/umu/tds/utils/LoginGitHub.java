package umu.tds.utils;

import java.io.IOException;

import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;

/**
 * Clase que permite la autenticación de un usuario en GitHub.
 * 
 * @version 1.0
 * @see <a href="https://github-api.kohsuke.org/">GitHub API</a>
 */

public enum LoginGitHub {
	/**
	 * Instancia única de la clase.
	 */
	INSTANCE;

	/**
	 * Verifica si un usuario está autenticado en GitHub.
	 * 
	 * @param usuario            Nombre de usuario de GitHub.
	 * @param githubPropertiesPath Ruta del fichero de propiedades de GitHub.
	 * @return true si el usuario está autenticado, false en caso contrario.
	 */
	public boolean verificar(String usuario, String githubPropertiesPath) {
		try {
			GitHub github = GitHubBuilder.fromPropertyFile(githubPropertiesPath).build();

			if (github.isCredentialValid()) {
				GHUser ghuser = github.getMyself();

				return (ghuser.getLogin().equals(usuario) && github.isCredentialValid());
			}
			return false;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}

}
