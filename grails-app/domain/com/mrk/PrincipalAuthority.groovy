package com.mrk

import org.apache.commons.lang.builder.HashCodeBuilder

class PrincipalAuthority implements Serializable {

	Principal user
	Authority authority

	boolean equals(other) {
		if (!(other instanceof PrincipalAuthority)) {
			return false
		}

		other.user?.id == user?.id &&
			other.authority?.id == authority?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (user) builder.append(user.id)
		if (authority) builder.append(authority.id)
		builder.toHashCode()
	}

	static PrincipalAuthority get(long userId, long authorityId) {
		find 'from UserAuthority where user.id=:userId and authority.id=:authorityId',
			[userId: userId, authorityId: authorityId]
	}

	static PrincipalAuthority create(Principal user, Authority authority, boolean flush = false) {
		new PrincipalAuthority(user: user, authority: authority).save(flush: flush, insert: true)
	}

	static boolean remove(Principal user, Authority authority, boolean flush = false) {
		PrincipalAuthority instance = PrincipalAuthority.findByUserAndAuthority(user, authority)
		instance ? instance.delete(flush: flush) : false
	}

	static void removeAll(Principal user) {
		executeUpdate 'DELETE FROM UserAuthority WHERE user=:user', [user: user]
	}

	static void removeAll(Authority authority) {
		executeUpdate 'DELETE FROM UserAuthority WHERE authority=:authority', [authority: authority]
	}

	static mapping = {
		id composite: ['authority', 'user']
		version false
	}
}
