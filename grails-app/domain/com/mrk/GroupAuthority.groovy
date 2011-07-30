package com.mrk

import org.apache.commons.lang.builder.HashCodeBuilder

class GroupAuthority implements Serializable {

	Group group
	Authority authority

	boolean equals(other) {
		if (!(other instanceof GroupAuthority)) {
			return false
		}

		other.group?.id == group?.id &&
			other.authority?.id == authority?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (group) builder.append(group.id)
		if (authority) builder.append(authority.id)
		builder.toHashCode()
	}

	static GroupAuthority get(long groupId, long authorityId) {
		println "here"
		
		find 'from GroupAuthority where group.id=:groupId and authority.id=:authorityId',
			[groupId: groupId, authorityId: authorityId]
	}

	static GroupAuthority create(Group group, Authority authority, boolean flush = false) {
		new GroupAuthority(group: group, authority: authority).save(flush: flush, insert: true)
	}

	static boolean remove(Group group, Authority authority, boolean flush = false) {
		GroupAuthority instance = GroupAuthority.findByGroupAndAuthority(group, authority)
		instance ? instance.delete(flush: flush) : false
	}

	static void removeAll(Group group) {
		executeUpdate 'DELETE FROM GroupAuthority WHERE group=:group', [group: group]
	}

	static void removeAll(Authority authority) {
		executeUpdate 'DELETE FROM GroupAuthority WHERE authority=:authority', [authority: authority]
	}

	static mapping = {
		id composite: ['authority', 'group']
		version false
	}
}
