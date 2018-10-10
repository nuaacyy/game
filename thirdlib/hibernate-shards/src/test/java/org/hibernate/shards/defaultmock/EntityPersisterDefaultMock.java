/**
 * Copyright (C) 2007 Google Inc.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.

 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.

 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 */

package org.hibernate.shards.defaultmock;

import org.hibernate.EntityMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.MappingException;
import org.hibernate.cache.access.EntityRegionAccessStrategy;
import org.hibernate.cache.entry.CacheEntryStructure;
import org.hibernate.engine.CascadeStyle;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.engine.ValueInclusion;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.hibernate.type.Type;
import org.hibernate.type.VersionType;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Map;

/**
 * @author Maulik Shah
 */
public class EntityPersisterDefaultMock implements EntityPersister {

    @Override
    public void postInstantiate() throws MappingException {
        throw new UnsupportedOperationException();
    }

    @Override
    public SessionFactoryImplementor getFactory() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getRootEntityName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getEntityName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSubclassEntityName(String entityName) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Serializable[] getPropertySpaces() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Serializable[] getQuerySpaces() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasProxy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasCollections() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasMutableProperties() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasSubselectLoadableCollections() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasCascades() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isMutable() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isInherited() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isIdentifierAssignedByInsert() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Type getPropertyType(String propertyName) throws MappingException {
        throw new UnsupportedOperationException();
    }

    @Override
    public int[] findDirty(final Object[] x,
                           final Object[] y,
                           final Object owner,
                           final SessionImplementor session) throws HibernateException {

        throw new UnsupportedOperationException();
    }

    public int[] findModified(final Object[] old,
                              final Object[] current,
                              final Object object,
                              final SessionImplementor session) throws HibernateException {

        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasIdentifierProperty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean canExtractIdOutOfEntity() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isVersioned() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Comparator getVersionComparator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public VersionType getVersionType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getVersionProperty() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasNaturalIdentifier() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int[] getNaturalIdentifierProperties() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] getNaturalIdentifierSnapshot(final Serializable id,
                                                 final SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public IdentifierGenerator getIdentifierGenerator() throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasLazyProperties() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object load(final Serializable id, final Object optionalObject, final LockMode lockMode,
                       final SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object load(Serializable id, Object optionalObject, LockOptions lockOptions, SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void lock(final Serializable id, final Object version, final Object object,
                     final LockMode lockMode, final SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void lock(Serializable id, Object version, Object object, LockOptions lockOptions, SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insert(final Serializable id, final Object[] fields, final Object object,
                       final SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Serializable insert(final Object[] fields,
                               final Object object,
                               final SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(final Serializable id,
                       final Object version,
                       final Object object,
                       final SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(final Serializable id,
                       final Object[] fields,
                       final int[] dirtyFields,
                       final boolean hasDirtyCollection,
                       final Object[] oldFields,
                       final Object oldVersion,
                       final Object object,
                       final Object rowId,
                       final SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Type[] getPropertyTypes() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String[] getPropertyNames() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean[] getPropertyInsertability() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean[] getPropertyUpdateability() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean[] getPropertyCheckability() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean[] getPropertyNullability() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean[] getPropertyVersionability() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean[] getPropertyLaziness() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CascadeStyle[] getPropertyCascadeStyles() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Type getIdentifierType() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getIdentifierPropertyName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCacheInvalidationRequired() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isLazyPropertiesCacheable() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasCache() {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityRegionAccessStrategy getCacheAccessStrategy() {
        throw new UnsupportedOperationException();
    }

    @Override
    public CacheEntryStructure getCacheEntryStructure() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ClassMetadata getClassMetadata() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isBatchLoadable() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isSelectBeforeUpdateRequired() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] getDatabaseSnapshot(final Serializable id,
                                        final SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getCurrentVersion(final Serializable id, final SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object forceVersionIncrement(final Serializable id,
                                        final Object currentVersion,
                                        final SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityMode guessEntityMode(final Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isInstrumented(final EntityMode entityMode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasInsertGeneratedProperties() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasUpdateGeneratedProperties() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isVersionPropertyGenerated() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void afterInitialize(final Object entity,
                                final boolean lazyPropertiesAreUnfetched,
                                final SessionImplementor session) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void afterReassociate(final Object entity, final SessionImplementor session) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object createProxy(final Serializable id, final SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean isTransient(final Object object, final SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] getPropertyValuesToInsert(final Object object,
                                              final Map mergeMap,
                                              final SessionImplementor session) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void processInsertGeneratedProperties(final Serializable id,
                                                 final Object entity,
                                                 final Object[] state,
                                                 final SessionImplementor session) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void processUpdateGeneratedProperties(final Serializable id,
                                                 final Object entity,
                                                 final Object[] state,
                                                 final SessionImplementor session) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class getMappedClass(final EntityMode entityMode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean implementsLifecycle(final EntityMode entityMode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean implementsValidatable(final EntityMode entityMode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Class getConcreteProxyClass(final EntityMode entityMode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPropertyValues(final Object object,
                                  final Object[] values,
                                  final EntityMode entityMode) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPropertyValue(final Object object,
                                 final int i,
                                 final Object value,
                                 final EntityMode entityMode) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] getPropertyValues(final Object object, final EntityMode entityMode) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getPropertyValue(final Object object,
                                   final int i,
                                   final EntityMode entityMode) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getPropertyValue(final Object object,
                                   final String propertyName,
                                   final EntityMode entityMode) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public Serializable getIdentifier(final Object object, final EntityMode entityMode) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Serializable getIdentifier(Object entity, SessionImplementor session) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public void setIdentifier(final Object object,
                              final Serializable id,
                              final EntityMode entityMode) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setIdentifier(Object entity, Serializable id, SessionImplementor session) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getVersion(final Object object, final EntityMode entityMode) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public Object instantiate(final Serializable id, final EntityMode entityMode) throws HibernateException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object instantiate(Serializable id, SessionImplementor session) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isInstance(final Object object, final EntityMode entityMode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasUninitializedLazyProperties(final Object object, final EntityMode entityMode) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    @Override
    public void resetIdentifier(final Object entity,
                                final Serializable currentId,
                                final Object currentVersion,
                                final EntityMode entityMode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void resetIdentifier(Object entity, Serializable currentId, Object currentVersion, SessionImplementor session) {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityPersister getSubclassEntityPersister(final Object instance,
                                                      final SessionFactoryImplementor factory,
                                                      final EntityMode entityMode) {
        throw new UnsupportedOperationException();
    }

    @Override
    public EntityMetamodel getEntityMetamodel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ValueInclusion[] getPropertyInsertGenerationInclusions() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ValueInclusion[] getPropertyUpdateGenerationInclusions() {
        throw new UnsupportedOperationException();
    }
}
