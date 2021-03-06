/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @author tags. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

package org.jboss.dmr.client.dispatch.impl;

import com.google.inject.Inject;
import org.jboss.dmr.client.dispatch.Action;
import org.jboss.dmr.client.dispatch.ActionHandler;
import org.jboss.dmr.client.dispatch.ActionType;
import org.jboss.dmr.client.dispatch.HandlerMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Heiko Braun
 * @date 3/17/11
 */
public class HandlerRegistry implements HandlerMapping {

    private Map<ActionType, ActionHandler> registry = new HashMap<ActionType, ActionHandler>();

    @Inject
    public HandlerRegistry(DMRHandler dmrhandler, UploadHandler uploadHandler) {
        register(ActionType.DMR, dmrhandler);
        register(ActionType.UPLOAD, uploadHandler);
    }

    @Override
    public ActionHandler resolve(Action action) {
        return registry.get(action.getType());
    }

    @Override
    public void register(ActionType actionType, ActionHandler handler)
    {
        registry.put(actionType, handler);
    }
}
