import sys

if len(sys.argv) < 2:
    print "Usage: python uml.py <uml> [<output_dir>]"
    exit()

stream = [ l[:-1] for l in open(sys.argv[1]).readlines()]
classes = []
output_dir = "." if len(sys.argv) < 3 else sys.argv[2]

for line in stream:
    tokens = line.split()

    if line == "" or line[0] == '#':
        continue

    if tokens[0] == 'class':
        extends = False if len(tokens) < 3 else (tokens[2] == '->')
        classes.append({
                "classname": tokens[1],         
                "name": "public class %s%s" % (tokens[1], " extends %s" % (tokens[-1]) if extends else ""),
                "properties": [],
                "methods": [], 
                "constructors" : [], 
                "super" : extends })
    
    if line[0] in ['+', '-', '*']:
    
        memmodifier = " static" if (line[1] == 's') else ""
        returntype = tokens[1]
        name = " ".join(tokens[2:])
        access = "private" if line[0] == '-' else ("public" if line[0] == '+' else "protected")
        #arguments = line.su
        sig = "%s%s %s %s" % (access, memmodifier, returntype, name)
        if ')' in name:
            if '(' in tokens[1]:
                classes[-1]["constructors"].append(sig)
            else:
                classes[-1]["methods"].append(sig)
        else:        
            classes[-1]["properties"].append(sig)



filename = ""
for c in classes:
    filename = "%s/%s.java" % (output_dir, c["name"].split()[2])
    content = ""
    f = open(filename, "w+")

    content = "%s {" % c["name"]
 
    # Add the properties first
    for prop in c["properties"]:
        content = ("%s\n\t%s;") % (content, prop)
    
    # Add the constructors including the empty constructor
    content = "%s\n\n\tpublic %s(){}" % (content, c["classname"])  if not c["super"] else content      
    for cons in c["constructors"]:
        content = ("%s\n\n\t%s { %s } ") % (content, cons, "super();" if c["super"] else "")
    
    # Add the methods
    for meth in c["methods"]:
        content = "%s\n\n\t%s { return null; }" % (content, meth)
    content = "%s\n}" % content

    f.write(content)
    f.close()

    print ">> %s" % filename
