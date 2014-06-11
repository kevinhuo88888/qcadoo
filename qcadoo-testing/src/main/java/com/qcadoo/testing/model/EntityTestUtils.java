/**
 * ***************************************************************************
 * Copyright (c) 2010 Qcadoo Limited
 * Project: Qcadoo Framework
 * Version: 1.3
 *
 * This file is part of Qcadoo.
 *
 * Qcadoo is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * ***************************************************************************
 */
package com.qcadoo.testing.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.qcadoo.model.api.DataDefinition;
import com.qcadoo.model.api.Entity;
import com.qcadoo.model.api.EntityList;

/**
 * This is a set of common entity testing helpers.
 * 
 * @since 1.2.1
 */
public final class EntityTestUtils {

    private EntityTestUtils() {
    }

    public static Entity mockEntity() {
        return Mockito.mock(Entity.class);
    }

    public static Entity mockEntity(final DataDefinition dataDefinition) {
        Entity entityMock = mockEntity();
        BDDMockito.given(entityMock.getDataDefinition()).willReturn(dataDefinition);
        return entityMock;
    }

    public static void stubId(final Entity entity, final Long id) {
        BDDMockito.given(entity.getId()).willReturn(id);
        stubField(entity, "id", id);
    }

    public static void stubField(final Entity entity, final String fieldName, final Object fieldValue) {
        BDDMockito.given(entity.getField(fieldName)).willReturn(fieldValue);
    }

    public static void stubField(final Entity entity, final String fieldName, final Answer<?> answer) {
        BDDMockito.given(entity.getField(fieldName)).willAnswer(answer);
    }

    public static void stubBooleanField(final Entity entity, final String fieldName, final boolean fieldValue) {
        BDDMockito.given(entity.getBooleanField(fieldName)).willReturn(fieldValue);
        stubField(entity, fieldName, fieldValue);
    }

    public static void stubStringField(final Entity entity, final String fieldName, final String fieldValue) {
        BDDMockito.given(entity.getStringField(fieldName)).willReturn(fieldValue);
        stubField(entity, fieldName, fieldValue);
    }

    public static void stubDateField(final Entity entity, final String fieldName, final Date fieldValue) {
        BDDMockito.given(entity.getDateField(fieldName)).willReturn(fieldValue);
        stubField(entity, fieldName, fieldValue);
    }

    public static void stubIntegerField(final Entity entity, final String fieldName, final Integer fieldValue) {
        BDDMockito.given(entity.getIntegerField(fieldName)).willReturn(fieldValue);
        stubField(entity, fieldName, fieldValue);
    }

    public static void stubDecimalField(final Entity entity, final String fieldName, final BigDecimal fieldValue) {
        BDDMockito.given(entity.getDecimalField(fieldName)).willReturn(fieldValue);
        stubField(entity, fieldName, fieldValue);
    }

    public static void stubManyToManyField(final Entity entity, final String fieldName, final Set<Entity> fieldValue) {
        Answer<Set<Entity>> answer = new Answer<Set<Entity>>() {

            @Override
            public Set<Entity> answer(final InvocationOnMock invocation) throws Throwable {
                return Sets.newHashSet(fieldValue);
            }
        };
        BDDMockito.given(entity.getManyToManyField(fieldName)).willAnswer(answer);
        stubField(entity, fieldName, answer);
    }

    public static void stubHasManyField(final Entity entity, final String fieldName, final Iterable<Entity> fieldValue) {
        EntityList entityListMock = EntityListMock.create(Lists.newArrayList(fieldValue));
        stubHasManyField(entity, fieldName, entityListMock);
    }

    public static void stubHasManyField(final Entity entity, final String fieldName, final EntityList fieldValue) {
        BDDMockito.given(entity.getHasManyField(fieldName)).willReturn(fieldValue);
        stubField(entity, fieldName, fieldValue);
    }

    public static void stubBelongsToField(final Entity entity, final String fieldName, final Entity fieldValue) {
        BDDMockito.given(entity.getBelongsToField(fieldName)).willReturn(fieldValue);
        stubField(entity, fieldName, fieldValue);
    }

}