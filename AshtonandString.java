class Result {

    /*
     * Complete the 'ashtonString' function below.
     *
     * The function is expected to return a CHARACTER.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER k
     */

    static char ashtonString(String a, int k) {

        TreeSet<String> subStringSet = new TreeSet<>();
        TreeSet<String> resultSet = new TreeSet<>();

        int index = 0;
        int len   = a.length();
        int tempIndex = 1;

        while (index < len){
            subStringSet.add(a.substring(index++));
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (true){

            String str = subStringSet.pollFirst();

            if(str.length() > 1){
                subStringSet.add(str.substring(1));
            }

            len   = str.length();
            tempIndex = 0;

            while (tempIndex++ < len){
                String res = str.substring(0, tempIndex);
                if(resultSet.add(res)){
                    stringBuilder.append(res);
                }
            }

            int strLen = stringBuilder.length();
            if(strLen > k){
                char ch = stringBuilder.charAt(k-1);
                resultSet.clear();
                subStringSet.clear();
                resultSet = null;
                subStringSet = null;
                stringBuilder = null;
                return ch;
            }
        }
    }

}
