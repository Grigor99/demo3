package com.example.demo.test;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    List<AssignmentApplicationBE> list = new ArrayList<>();

    private void fill() {

            for (long i = 1; i < 160; i++) {
                AssignmentApplicationBE be = new AssignmentApplicationBE();
                be.setNodeId(i);
                list.add(be);
            }
            AssignmentApplicationBE be = new AssignmentApplicationBE();
            be.setNodeId(0L);
            list.add(be);
            AssignmentApplicationBE be1 = new AssignmentApplicationBE();
            be1.setNodeId(0L);
            list.add(be1);

    }

    Map<Long, List<Long>> relevantModules = new ConcurrentHashMap<>();
    Map<Long, List<Long>> notRelevantModules = new ConcurrentHashMap<>();
    List<Long> componentIds = Collections.synchronizedList(new ArrayList<>());


    private void run(AssignmentApplicationBE assignmentApplicationBE, Map<Long, List<Long>> relevantModules,
                     List<Long> componentIds) {
        Long nodeId = assignmentApplicationBE.getNodeId();
        componentIds.add(nodeId);

        relevantModules.putIfAbsent(nodeId, new ArrayList<>());

        List<Long> otherOemAppCodes = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        // UC 5
        relevantModules.get(nodeId).addAll(otherOemAppCodes);
    }

    private void testMain() {
        ExecutorService executorFirst = Executors.newFixedThreadPool(Math.min(5, Runtime.getRuntime().availableProcessors()));

        for (int i = 0; i < list.size(); i += 150) {
            List<AssignmentApplicationBE> batch = list.subList(i, Math.min(i + 150, list.size()));

            List<CompletableFuture<Void>> futures = new ArrayList<>();
            for (AssignmentApplicationBE each : batch) {
                CompletableFuture<Void> future = CompletableFuture.runAsync(() ->
                                run(each, relevantModules, componentIds),
                        executorFirst
                );
                futures.add(future);
            }
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        }
        executorFirst.shutdown();
    }

    public static void main(String[] args) {

        Main main = new Main();

        Map<Long,Long> map = new ConcurrentHashMap<>();
        System.out.println(map.getOrDefault(4L,-1L));
//        List<Long> l1 = new ArrayList<>();
//        l1.add(3L);
//        l1.add(4L);
//        map.put(4L, l1);
//        List<Long> l2 = new ArrayList<>();
//        l2.add(5L);
//        l2.add(4L);
//        map.put(5L, l2);
//        System.out.println(main.convertOemInformation(map));
    }

    private Map<Long, List<Long>> convertOemInformation(Map<Long, List<Long>> oemInformation) {
        Map<Long, List<Long>> convertedOemInformation = new HashMap<>();
        List<Long> rootNodeids = new ArrayList<>();
        for (Map.Entry<Long, List<Long>> entry : oemInformation.entrySet()) {
            for (Long rootNodeid : entry.getValue()) {
                rootNodeids.add(rootNodeid);
            }
            convertedOemInformation.put(entry.getKey(), new ArrayList<Long>());
        }
        Map<Long, Long> nodeidNodeIdMap = new HashMap<>();
        nodeidNodeIdMap.put(4l,6l);
        nodeidNodeIdMap.put(5l,6l);
        nodeidNodeIdMap.put(7l,6l);
        for (Map.Entry<Long, List<Long>> entry : oemInformation.entrySet()) {
            for (Long rootNodeid : entry.getValue()) {
                convertedOemInformation.get(entry.getKey()).add(nodeidNodeIdMap.get(rootNodeid));
            }
        }
        return convertedOemInformation;
    }
}
