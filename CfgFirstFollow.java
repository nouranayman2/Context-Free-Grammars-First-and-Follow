package csen1002.main.task6;

import java.util.*;

public class CfgFirstFollow {

    String[] variables;
    String[] characters;

    String Sequences;

    ArrayList<String> sequence = new ArrayList<>();

    ArrayList<String> Prods;

    HashMap<String, ArrayList<String>> map = new HashMap<>();

    /**
     * Constructs a Context Free Grammar
     *
     * @param cfg A formatted string representation of the CFG. The string
     *            representation follows the one in the task description
     */
    public CfgFirstFollow(String cfg) {


        String[] x = cfg.split("#");
        variables = x[0].split(";");
        characters = x[1].split(";");
        Sequences = x[2];
        String[] x2 = Sequences.split(";");
        Prods = new ArrayList<>();
        for (int i = 0; i < x2.length; i++) {
            Prods.add(x2[i]);
        }
        // TODO Auto-generated constructor stub
    }

    /**
     * Calculates the First Set of each variable in the CFG.
     *
     * @return A string representation of the First of each variable in the CFG,
     * formatted as specified in the task description.
     */
    public String first() {

        for (String s : characters) {
            ArrayList<String> set = new ArrayList<>();
            set.add(s);
            map.put(s, set);
        }
        ArrayList<String> set = new ArrayList<>();
        set.add("e");
        map.put("e", set);


        for (String var : variables) {
            ArrayList<String> setv = new ArrayList<>();
            map.put(var, setv);
        }
        boolean change = true;
        while (change) {
            change = false;
            for (String x : Prods) {
                String[] arr = x.split("/");
                String variable = arr[0];
                String rules = arr[1];
                String[] arr2 = rules.split(",");
                for (String r : arr2) {
                    boolean flag = true;
                    for (int i = 0; i < r.length(); i++) {
                        ArrayList<String> q = map.get(r.charAt(i) + "");
                        if (!q.contains("e")) {
                            flag = false;
                        }
                    }
                    if (flag == true) {
                        ArrayList<String> q = map.get(variable);
                        if (!q.contains("e")) {
                            q.add("e");
                            change = true;
                        }
                    }
                    for (int i = 0; i < r.length(); i++) {
                        boolean flagx = true;
                        for (int k = 0; k < i; k++) {
                            ArrayList<String> q = map.get(r.charAt(k) + "");
                            if (!q.contains("e")) {
                                flagx = false;
                            }
                        }
                        if (flagx == true) {
                            ArrayList<String> q = map.get(r.charAt(i) + "");
                            ArrayList<String> q2 = map.get(variable);
                            for (int j = 0; j < q.size(); j++) {
                                if (!q2.contains(q.get(j)) && !q.get(j).equals("e")) {
                                    q2.add(q.get(j));
                                    change = true;
                                }
                            }

                        }


                    }

                }

            }

        }

        String output = "";

        // TODO Auto-generated method stub
        for (String a : variables) {
            ArrayList<String> q = map.get(a);
            Collections.sort(q);
            output += a + "/" + String.join("", q) + ";";

        }
        output = output.substring(0, output.length() - 1);
        return output;
    }

    /**
     * Calculates the Follow Set of each variable in the CFG.
     *
     * @return A string representation of the Follow of each variable in the CFG,
     * formatted as specified in the task description.
     */
    public String follow() {

        first();
        System.out.println(map);

        HashMap<String, ArrayList<String>> mapx = new HashMap<>();

        for (String var : variables) {
            ArrayList<String> setv = new ArrayList<>();
            mapx.put(var, setv);
        }
 //System.out.println(mapx);
        mapx.get("S").add("$");
        boolean flag = true;
        while (flag) {
            flag = false;
            for (String x : Prods) {
                String[] arr = x.split("/");
                String variable = arr[0];
                String rules = arr[1];
                String[] arr2 = rules.split(",");
                for (String r : arr2) {
                    for (int i = 0; i < r.length(); i++) {
                        ArrayList<String> set = new ArrayList<>(List.of(variables));
                        if (set.contains(r.charAt(i) + "")) { //change char to string
                            int b = i + 1;
                            for (; b < r.length(); b++) {
                                ArrayList<String> o = map.get(r.charAt(b) + ""); //first
                                ArrayList<String> w = mapx.get(r.charAt(i) + "");
                                for (int z = 0; z < o.size(); z++) {
                                    if (!w.contains(o.get(z)) && !o.get(z).equals("e")) {
                                        w.add(o.get(z)); //putting first in mapx
                                        flag = true;

                                    }

                                }
                                if (!o.contains("e")) {
                                    break;

                                }


                            }
                            if (b == r.length()) {
                                ArrayList<String> o = mapx.get(variable); //first
                                ArrayList<String> w = mapx.get(r.charAt(i) + "");
                                for (int z = 0; z < o.size(); z++) {
                                    if (!w.contains(o.get(z)) && !o.get(z).equals("e")) {
                                        w.add(o.get(z)); //putting first in mapx
                                        flag = true;


                                    }
                                }
                            }
                        }

                    }

                }
            }


        }


        // TODO Auto-generated method stub
        String output = "";

        // TODO Auto-generated method stub
        for (String a : variables) {
            ArrayList<String> q = mapx.get(a);
            Collections.sort(q);
            output += a + "/" + String.join("", q) + ";";

        }
        output = output.substring(0, output.length() - 1);
        return output;
    }

    public static void main(String[] args) {
        String cfg = ("S;T;L#a;b;c;d;i#S/ScT,La,Ti,b;T/aSb,LabS,i;L/SdL,Si");

        CfgFirstFollow mycfg = new CfgFirstFollow(cfg);
        mycfg.follow();


        System.out.print(mycfg.Prods);
        System.out.print(Arrays.toString(mycfg.characters));


        // mycfg.eliminateUnitRules();

        //  mycfg.eliminateEpsilonRules();
        //     System.out.println("my cfg" + mycfg);

//        //   System.out.println("Final " + mycfg.toString());


        // TODO Auto-generated method stub
    }


}


