/* abc - The AspectBench Compiler
 * Copyright (C) 2004 Aske Simon Christensen
 * Copyright (C) 2004 Ganesh Sittampalam
 * Copyright (C) 2004 Damien Sereni
 * Copyright (C) 2006 Eric Bodden
 *
 * This compiler is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This compiler is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this compiler, in the file LESSER-GPL;
 * if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA.
 */

package abc.weaving.aspectinfo;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import polyglot.util.Position;
import abc.weaving.matching.MatchingContext;
import abc.weaving.residues.AlwaysMatch;
import abc.weaving.residues.ContextValue;
import abc.weaving.residues.Residue;

/** An argument pattern denoting any type.
 *  @author Aske Simon Christensen
 *  @author Ganesh Sittampalam
 *  @author Damien Sereni
 *  @author Eric Bodden
 */
public class ArgAny extends ArgPattern {
    public ArgAny(Position pos) {
        super(pos);
    }

    public Residue matchesAt(MatchingContext mc,ContextValue cv) {
        return AlwaysMatch.v();
    }

    public String toString() {
        return "*";
    }

    public Var substituteForPointcutFormal
        (Hashtable/*<String,Var>*/ renameEnv,
         Hashtable/*<String,AbcType>*/ typeEnv,
         Formal formal,
         List/*<Formal>*/ newLocals,
         List /*<CastPointcutVar>*/ newCasts,
         Position pos) {

        String name=Pointcut.freshVar();
        Var v=new Var(name,pos);

        newLocals.add(new Formal(formal.getType(),name,pos));

        return v;
    }

    public void getFreeVars(Set/*<String>*/ result) {}

    /* (non-Javadoc)
         * @see abc.weaving.aspectinfo.ArgPattern#unify(abc.weaving.aspectinfo.ArgPattern, abc.weaving.aspectinfo.Unification)
         */
        public boolean unify(ArgPattern other, Unification unification) {
                if (other.getClass() == this.getClass()) {
                        unification.setArgPattern(this);
                        return true;
                } else return false;
        }
}