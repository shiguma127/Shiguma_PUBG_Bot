package xyz.shiguma.pubgpick;

import twitter4j.Status;
import twitter4j.UserMentionEntity;
import twitter4j.UserStreamAdapter;
import xyz.shiguma.pubgpick.enums.Erangel;
import xyz.shiguma.pubgpick.enums.Miramar;
import xyz.shiguma.pubgpick.enums.Sunhok;
import xyz.shiguma.pubgpick.enums.Weapon;
import xyz.shiguma.pubgpick.utils.Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.util.*;

public class StreamListener extends UserStreamAdapter {

    private final Main main;

    private SecureRandom r = new SecureRandom();

    //コンストラクタ - constructor
    public StreamListener(Main main) {
        this.main = main;
    }

    @Override
    //時間出力
    public void onStatus(Status status) {
        //コンソールツイート表示
        System.out.println("TimeLine: @" + status.getUser().getScreenName() + ": " + status.getText());

        String body = status.getText();
        ArrayList<Pair<Integer, Integer>> entities = new ArrayList<>();
        boolean isReply = false;
        for (UserMentionEntity userMentionEntity : status.getUserMentionEntities()) {
            if (userMentionEntity.getScreenName().toLowerCase().equals("shigubot")) isReply = true;
            entities.add(new Pair<>(userMentionEntity.getStart(), userMentionEntity.getEnd()));
        }
        entities.sort((a, b) -> b.first - a.second);
        for (Pair<Integer, Integer> entity : entities) {
            StringBuilder builder = new StringBuilder(body);
            builder.replace(entity.first, entity.second, "");
            body = builder.toString();
        }
        if (!isReply) return;

        if (status.isRetweet()) return;
        body = body.trim();
        String[] arg = body.split(" ");
        System.out.println(Arrays.toString(arg));
        System.out.println(body);

        if (arg.length <= 0) {
            //this.main.sendReply(status, ""); if command is not exist
            return;
        }
        if (status.getSource().contains("ShigumaTwBOT")) {
            Utils.log(status.getText());
            return;
        }
        if (Utils.isWindows()) {
            switch (arg[0]) {
                case "!NcOff":
                    this.main.enableNc = false;
                    if (status.getUser().getScreenName().equals(this.main.tmpName)) {
                        this.main.sendReply(status, "[info] disabled Name Changer");
                    } else if (!this.main.tmpName.isEmpty()) {
                        this.main.asyncTwitter.updateProfile(this.main.tmpName, "", "", "");
                        Utils.Println("変更されたユーザー名に戻します\n" + "変更されたユーザー名 :" + this.main.tmpName);

                        this.main.sendReply(status, "[info] disabled Name Changer");
                        break;
                    } else {
                        this.main.asyncTwitter.updateProfile(this.main.defaultUsername, "", "", "");
                        this.main.sendReply(status, "[info] disabled Name Changer");
                        Utils.Println("コンフィグのデフォルトユーザ名に戻します。\n" + "デフォルトユーザ名 :" + (this.main.defaultUsername));
                        break;
                    }
                    this.main.asyncTwitter.updateProfile(this.main.defaultUsername, "", "", "");
                    this.main.sendReply(status, "[info] disabled Name Changer");
                    break;

                case "!NcOn":
                    this.main.enableNc = true;
                    this.main.sendReply(status, "[info] enabled Name Changer");
                    break;
            }
        }

        if (this.main.enableNc && status.getText().startsWith("!nc") && (status.getUser().getScreenName().startsWith("shiguma127"))) {
            this.main.tmpName = arg[1];
            this.main.asyncTwitter.updateProfile(arg[1], "", "", "");
            Utils.Println("ユーザー名が変更されました" + " :" + this.main.tmpName);
        } else if (status.getText().startsWith("!nc") && (!status.getUser().getScreenName().startsWith("shiguma127"))) {
            this.main.asyncTwitter.updateStatus("権限がありません。\n" + "@" + status.getUser().getScreenName());
        }

        if (!this.main.isTweetable) {
            return;
        }
/*
        if (status.getText().contains("どれぐらい好き？")) {
            int value = this.r.nextInt(100) + 1;
            if (value == 1) {
                this.main.sendReply(status, " いっぱいちゅき");
            } else if (value < 25) {
                this.main.sendReply(status, "カツ丼食えよ!");
            } else if (value < 60) {
                this.main.sendReply(status, "自分が何やったかわかっとるんか・・・");
            } else {
                this.main.sendReply(status, "そういうの一番嫌い。");
            }
            return;
        }
        if (status.getText().contains("ぬるぽ")) {
            this.main.sendReply(status, "ガッ");
            return;
        }

        if (status.getText().contains("ラッキーアイテム") && !status.getSource().contains("PUBG_Fall_Place_Picker")) {
            this.main.sendReply(status, "今日のラッキーアイテムは" + main.luckyItems.get(this.r.nextInt(main.luckyItems.size())) + "です。");
            return;
        }
        if (status.getText().contains("!アイテム総数")) {
            this.main.sendReply(status, "アイテム総数は" + main.luckyItems.size() + "個です。");
        }
        if (status.getText().startsWith("#しぐまんアイテム")) {
            String item = arg[0].replace("#しぐまんアイテム", "").trim();
            if (item.isEmpty()) {
                this.main.sendReply(status, "ハッシュタグのあとにスペースなどを挿入しないでください");
                return;
            }
            if (main.luckyItems.contains(item)) {
                this.main.sendReply(status, "すでに登録されています。");
                return;
            }
            main.luckyItemWriter.println(item);
            main.luckyItems.add(item);
            this.main.sendReply(status, "登録が完了しました: " + item);
        }

*/
        switch (arg[0]) {
            case "pick":
                if (arg.length <= 1) {
                    this.main.sendReply(status, "マップを選んでください。-EでErangel,-MでMiramarを選択できます。詳しい使い方はhelpで確認できます。");
                    return;
                }
                switch (arg[1].toLowerCase()) {
                    case "-m":///Miramar
                        Miramar random = Miramar.getRundom();
                        this.main.sendMediaReply(status, random.getname() + " が選ばれました。", "Media/Miramar/" + random.getname() + ".jpg");
                        break;

                    case "-e"://Erangel
                        Erangel randome = Erangel.getRandom();
                        this.main.sendMediaReply(status, randome.getname() + " が選ばれました。", "Media/Erangel/" + randome.getname() + ".jpg");
                        break;

                    case "-s":
                        Sunhok randoms = Sunhok.getRandom();
                        this.main.sendReply(status, randoms.getName() + " が選ばれました。");
                        break;

                    default:
                        this.main.sendReply(status, "-eでErangel,-mでMiramar,-sでSavageを選択できます。");
                        break;
                }
                break;
                //aaa

            case "島":
                Erangel random = Erangel.getRandom();
                this.main.sendMediaReply(status, random.getname() + " が選ばれました。", "Media/Erangel/" + random.getname() + ".jpg");
                break;

            case "砂漠":
                Miramar randome = Miramar.getRundom();
                this.main.sendMediaReply(status, randome.getname() + " が選ばれました。", "Media/Miramar/" + randome.getname() + ".jpg");
                break;

            case "森":
                Sunhok randoms = Sunhok.getRandom();
                this.main.sendMediaReply(status, randoms.getName() + " が選ばれました。", "Media/Sunhok/" + randoms.getName() + ".jpg");
                break;


            case "help":
                this.main.sendReply(status, "!pickのあとに-Eで島マップ,-Mで砂漠マップ,-SでSanhok,の地名をランダムで選びます。[使用例:!pick -E]\n※大文字小文字は区別されません。" + "\n");
                break;

            case "winner":
                this.main.sendReply(status, "勝った！勝った！夕飯はドン勝だ！！");
                break;

            case "チャレンジ":
            case "Ch":
                Weapon Crandam = Weapon.getRandom();
                int rm = new SecureRandom().nextInt(10)+1;
                this.main.sendReply(status, Crandam.getname() + "で" + rm+ "キル");

            case "!spec":
                if (Utils.isWindows()) {
                    this.main.sendReply(status,
                            "CPUName: " + this.main.cpuName + "\n" +
                                    "GPUName: " + this.main.gpuName

                    );
                }

                break;
                /*
            case "!gpustatus":
                if (Utils.isWindows()) {
                    this.main.asyncExecutor.execute(() -> {
                        Components components = JSensors.get.components();
                        StringBuilder message = new StringBuilder();
                        List<Gpu> GPUs = components.gpus;
                        if (GPUs != null) {
                            for (final Gpu gpu : GPUs) {
                                System.out.println(gpu);
                                if (gpu.sensors != null) {
                                    System.out.println("GPU Loads: ");
                                    List<Load> loads = gpu.sensors.loads;
                                    for (final Load load : loads) {
                                        message.append(load.name).append(": ").append(String.format("%.1f", load.value)).append("%\n");
                                    }
                                    //Print temperatures
                                    List<Temperature> temps = gpu.sensors.temperatures;
                                    for (final Temperature temp : temps) {
                                        message.append(temp.name).append(": ").append(temp.value).append("℃\n");
                                    }
                                }
                            }
                        }
                        System.out.println(message.toString().trim());
                        this.main.sendReply(status, message.toString().trim());
                    });
                    break;

                } else {
                    this.main.sendReply(status, "現在Linux上で作動しているため取得できません。");
                    break;
                }


            case "!cpustatus":
                if (Utils.isWindows()) {
                    this.main.asyncExecutor.execute(() -> {
                        Components components = JSensors.get.components();
                        StringBuilder message = new StringBuilder();
                        List<Cpu> CPUs = components.cpus;
                        if (CPUs != null) {
                            for (final Cpu cpu : CPUs) {
                                if (cpu.sensors != null) {
                                    System.out.println("CPU Loads: ");
                                    List<Load> loads = cpu.sensors.loads;
                                    for (final Load load : loads) {
                                        message.append(load.name.replace("Load CPU ", "")).append(": ").append(String.format("%.1f", load.value)).append("%\n");
                                    }
                                }
                            }
                        }
                        System.out.println(message.toString().trim());
                        this.main.sendReply(status, message.toString().trim());
                    });
                    break;
                } else {
                    this.main.sendReply(status, "現在Linux上で作動しているため取得できません。");
                    break;
                }
            case "!pcstatus":
                if (!Utils.isWindows()) {
                    this.main.sendReply(status, "現在Linux上で作動しているため取得できません。");
                    break;
                }
                this.main.asyncExecutor.execute(() -> {
                    Components components = JSensors.get.components();

                    List<Cpu> CPUs = components.cpus;
                    List<Gpu> GPUs = components.gpus;

                    Double total = null;
                    Double loadMemory = null;
                    Double loadGPU = null;
                    Double vRam = null;
                    Double gputemps = null;

                    for (final Cpu cpu : CPUs) {
                        System.out.println("CPU Loads: ");
                        for (final Load load : cpu.sensors.loads) {
                            System.out.println(load.name);
                            if (load.name.equals("Load CPU Total")) {
                                total = load.value;
                            } else if (load.name.equals("Load Memory")) {
                                loadMemory = load.value;
                            }
                        }
                    }
                    for (final Gpu gpu : GPUs) {
                        for (final Load load : gpu.sensors.loads) {
                            if (load.name.equals("Load GPU Core")) {
                                loadGPU = load.value;
                            } else if (load.name.equals("Load GPU Memory")) {
                                vRam = load.value;
                            }
                        }
                        for (final Temperature temp : gpu.sensors.temperatures) {
                            if (temp.name.equals("Temp GPU Core")) {
                                gputemps = temp.value;
                            }
                        }
                    }
                    if (total == null || loadMemory == null || loadGPU == null || vRam == null || gputemps == null) {
                        this.main.sendReply(status, "取得できませんでした。");
                        return;
                    }
                    String msg =
                            "CPU使用率: " + total + "%\n" +
                                    "Ram使用率: " + loadMemory + "%\n" +
                                    "GPU使用率: " + loadGPU + "%\n" +
                                    "VRam使用率: " + vRam + "%\n" +
                                    "GPU温度: " + gputemps + "℃\n";

                    System.out.println(msg);
                    this.main.sendReply(status, msg);
                });
                break;
                */

            case "lastlog":
                List<String> readlog = new ArrayList<>();
                try {
                    readlog = Files.readAllLines(Paths.get("botLog.txt"), StandardCharsets.UTF_8);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                this.main.readLog = readlog;
                this.main.sendReply(status, "[最終ログ]:" + readlog.get(readlog.size() - 1));
                break;
        }
    }

    class Pair<A, B> {
        private A first;
        private B second;

        private Pair(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return this.first;
        }

        public B getSecond() {
            return this.second;
        }
    }
}
