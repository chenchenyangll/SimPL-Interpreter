/* Generated By:JJTree: Do not edit this line. ASTAdditiveExp.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=false,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package edu.sjtu.simpl.grammar;

public
class ASTAdditiveExp extends SimpleNode {
  public ASTAdditiveExp(int id) {
    super(id);
  }

  public ASTAdditiveExp(SimPL p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(SimPLVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=f1519be400839d7a6837a0f6ee698df1 (do not edit this line) */
