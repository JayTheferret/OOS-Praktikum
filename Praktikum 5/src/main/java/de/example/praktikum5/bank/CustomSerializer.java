package de.example.praktikum5.bank;

import com.google.gson.*;

import java.lang.reflect.Type;

public class CustomSerializer implements JsonSerializer<Transaction>, JsonDeserializer<Transaction> {

    /**
     * custom deserializer for Transactions
     *
     * @param jsonElement
     * @param type
     * @param jsonDeserializationContext
     * @return specific Transaction
     * @throws JsonParseException
     */
    @Override
    public Transaction deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObj = jsonElement.getAsJsonObject();
        JsonObject jsonInst = jsonObj.get("INSTANCE").getAsJsonObject();

        //new Gson().fromJson(element, Payment.class);

        if(jsonObj.get("CLASSNAME").getAsString().equals("Payment")){
            var pay = new Payment(
                    jsonInst.get("date").getAsString(),
                    jsonInst.get("amount").getAsDouble(),
                    jsonInst.get("description").getAsString()
            );
            pay.setOutgoingInterest(jsonInst.get("outgoingInterest").getAsDouble());
            pay.setIncomingInterest(jsonInst.get("incomingInterest").getAsDouble());

            return pay;
        }

        if(jsonObj.get("CLASSNAME").getAsString().equals("IncomingTransfer") || jsonObj.get("CLASSNAME").getAsString().equals("OutgoingTransfer")){
            var trans = new Transfer(
                    jsonInst.get("date").getAsString(),
                    jsonInst.get("amount").getAsDouble(),
                    jsonInst.get("description").getAsString(),
                    jsonInst.get("sender").getAsString(),
                    jsonInst.get("recipient").getAsString()
            );
            if(jsonObj.get("CLASSNAME").getAsString().equals("IncomingTransfer")){
                return new IncomingTransfer(trans);
            }
            else if(jsonObj.get("CLASSNAME").getAsString().equals("OutgoingTransfer")){
                return new OutgoingTransfer(trans);
            }
        }
        return null;
    }

    /**
     * custom serializer for accounts
     *
     * @param transaction
     * @param type
     * @param jsonSerializationContext
     * @return transaction as json
     */
    @Override
    public JsonElement serialize(Transaction transaction, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jtrans = new JsonObject();

        jtrans.addProperty("CLASSNAME", transaction.getClass().getSimpleName());
        jtrans.add("INSTANCE",new Gson().toJsonTree(transaction));
        return jtrans;
    }


}
