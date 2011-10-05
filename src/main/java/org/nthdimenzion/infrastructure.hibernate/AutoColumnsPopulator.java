package org.nthdimenzion.infrastructure.hibernate;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class AutoColumnsPopulator extends EmptyInterceptor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] currentState, String[] propertyNames, Type[] types) {
	setValue(currentState, propertyNames, "createdBy", "Template User");
	setValue(currentState, propertyNames, "createdTxTimestamp", new Timestamp(new Date().getTime()));
	setValue(currentState, propertyNames, "updatedBy", "Template User");
	setValue(currentState, propertyNames, "updatedTxTimestamp", new Timestamp(new Date().getTime()));
	return true;
	}

	private void setValue(Object[] currentState, String[] propertyNames, String propertyToSet, Object value) {
	int index = Arrays.asList(propertyNames).indexOf(propertyToSet);
	if (index >= 0) {
		currentState[index] = value;
	}
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
	setValue(currentState, propertyNames, "updatedBy", "Template User");
	setValue(currentState, propertyNames, "updatedTxTimestamp", new Timestamp(new Date().getTime()));
	return true;
	}

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
	super.onDelete(entity, id, state, propertyNames, types);
	}

	@Override
	public String onPrepareStatement(String sql) {
	return super.onPrepareStatement(sql);
	}
}